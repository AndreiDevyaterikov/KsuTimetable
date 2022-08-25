create or replace procedure add_buildings(_data character varying)
    language plpgsql
AS
$$
declare
    item jsonb;
    data_json jsonb;
begin
    data_json = to_jsonb(_data::jsonb);
    for index in 0.. jsonb_array_length(data_json) - 1
        loop
            item = data_json -> index; -- _data[index]
            if item ::jsonb ->> 'title' not in -- _data[index].get('title')
               ('ДОТ', 'СДО', 'Военный учебный центр', 'Организация', 'Гл.', 'Гл', 'Спортивный корпус') then
                if item ::jsonb ->> 'id' not in (select building_id from buildings) then
                    insert into buildings (building_id, building_name) values (item ::jsonb ->> 'id', item ::jsonb ->> 'title');
                end if;
            end if;
        end loop;
end
$$;

create or replace procedure add_cabinet(_data character varying)
    language plpgsql
AS
$$
declare
    item     jsonb;
    building jsonb;
    data_json jsonb;
begin
    data_json = to_jsonb(_data::jsonb);
    for index in 0.. jsonb_array_length(data_json) - 1
        loop
            item = data_json -> index; -- _data[index]
            building = item ::jsonb ->> 'building';
            raise notice 'value id % = %',index, building;
            if building ::jsonb ->> 'id' in (select building_id from buildings) then
                if item ::jsonb ->> 'id' not in (select cabinet_id from cabinets) then
                    insert into cabinets (cabinet_id, cabinet_name, building_id)
                    values (item ::jsonb ->> 'id', item ::jsonb ->> 'title', building ::jsonb ->> 'id');

                    insert into logbook(log_id, status, time_changed, week_day, cabinet_id, teacher_id)
                    values (nextval('logs_sequence'), 'Аудитория свободна', localtimestamp ,EXTRACT(ISODOW FROM localtimestamp), item ::jsonb ->> 'id', null);
                end if;
            end if;
        end loop;
end ;
$$;


create or replace procedure load_timetable(_data character varying)
    language plpgsql
as
$$
declare
    item jsonb;
    data_json jsonb;
begin
    data_json = to_jsonb(_data::jsonb);
    for index in 0.. jsonb_array_length(data_json) - 1
        loop
        item = data_json -> index;

        end loop;
end;
$$;


create or replace procedure add_timetable(_data character varying)
    language plpgsql
AS
$$
declare
    item    jsonb;
    cabinet jsonb;
    _group  jsonb;
    teacher jsonb;
    data_json jsonb;
begin
    data_json = to_jsonb(_data::jsonb);
    for index in 0.. jsonb_array_length(data_json) - 1
        loop
            item = data_json -> index; -- timetable
            cabinet = item ::jsonb ->> 'cabinet';
            _group = item ::jsonb ->> 'group';
            teacher = item ::jsonb ->> 'teacher';
            raise notice 'value id % = %',index, cabinet;
            if
                            cabinet ::jsonb ->> 'id' in (select cabinet_id from cabinets) and
                            _group ::jsonb ->> 'id' in (select group_id from groups) and
                            teacher ::jsonb ->> 'id' in (select  from users) then
                insert into timetable (lesson_id, lesson_day, lesson_name, lesson_number, lesson_time_end,
                                       lesson_time_start, lesson_type, cabinet_id, group_id, teacher_id)
                values (nextval('timetable_sequence'),
                        cast(item ::jsonb ->> 'lessonDay' as numeric),
                        item ::jsonb ->> 'lessonName',
                        cast(item ::jsonb ->> 'lessonNumber' as numeric),
                        item ::jsonb ->> 'lessonTimeEnd',
                        item ::jsonb ->> 'lessonTimeStart',
                        item ::jsonb ->> 'lessonType',
                        cabinet ::jsonb ->> 'id',
                        _group ::jsonb ->> 'id',
                        teacher ::jsonb ->> 'id');
            end if;
        end loop;
end ;
$$;

create or replace function get_current_log(_cabinetId varchar) returns logbook
    language plpgsql
as
$$
declare
    last_log logbook;
begin
    SET TIMEZONE = 'EUROPE/MOSCOW';
    select *
    from logbook
    where cabinet_id = _cabinetId
    order by time_changed desc
    limit 1
    into last_log;

    return last_log;
end;
$$;


create or replace procedure write_log(_cabinet character varying, _teacher character varying, _status varchar)
    language plpgsql
as
$$

BEGIN
    SET TIMEZONE = 'EUROPE/MOSCOW';

    insert into logbook(log_id,
                     status,
                     time_changed,
                     week_day,
                     cabinet_id,
                     teacher_id)
    values (nextval('logs_sequence'),
            _status,
            localtimestamp
               , EXTRACT(ISODOW FROM localtimestamp),
            _cabinet,
            _teacher);
end;
$$;



create or replace function get_current_lesson_in_cabinet(_cabinetId varchar) returns timetable
    language plpgsql
as
$$
declare
    lesson timetable;
begin
    SET TIMEZONE = 'EUROPE/MOSCOW';
    select *
    from  timetable
    where lesson_day = EXTRACT(ISODOW FROM localtimestamp)
      and cast(localtime as varchar) between lesson_time_start and lesson_time_end
      and  cabinet_id =  _cabinetId
    into lesson;

    return lesson;
end;
$$;

create sequence if not exists logs_sequence
increment 1
start 1;

create sequence if not exists timetable_sequence
increment 1
start 1;