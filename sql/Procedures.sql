create or replace procedure add_buildings(_data character varying)
    language plpgsql
AS
$$
declare
    item      jsonb;
    data_json jsonb;
begin
    data_json = to_jsonb(_data::jsonb);
    for index in 0.. jsonb_array_length(data_json) - 1
        loop
            item = data_json -> index; -- _data[index]
            if item ::jsonb ->> 'title' not in -- _data[index].get('title')
               ('ДОТ', 'СДО', 'Военный учебный центр', 'Организация', 'Гл.', 'Гл', 'Спортивный корпус') then
                if item ::jsonb ->> 'id' not in (select building_id from buildings) then
                    insert into buildings (building_id, building_name)
                    values (item ::jsonb ->> 'id', item ::jsonb ->> 'title');
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
    item      jsonb;
    building  jsonb;
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
                    values (nextval('logs_sequence'), 'Аудитория свободна', localtimestamp,
                            EXTRACT(ISODOW FROM localtimestamp), item ::jsonb ->> 'id', null);
                end if;
            end if;
        end loop;
end ;
$$;


create procedure add_timetable(_data character varying)
    language plpgsql
as
$$
declare
    _lesson            jsonb; --one row of timetable
    _timetable         jsonb;
    _group             jsonb;
    _lesson_time_start varchar;
    _lesson_time_end   varchar;
    _lesson_name       varchar;
    _lesson_number     integer;
    _lesson_day        integer;
    _cabinet_name      varchar;
    _cabinet_id        varchar;
    _teacher_name      varchar;
    _teacher_id        varchar;
    _lesson_type       varchar;

begin
    _timetable = to_jsonb(_data::jsonb);
    for index in 0.. jsonb_array_length(_timetable) - 1
        loop
            _lesson = _timetable ->> index;
            _group = _lesson ::jsonb ->> 'group';
            _lesson_day = cast(_lesson ::jsonb ->> 'y' as numeric);
            _lesson_number = cast(_lesson ::jsonb ->> 'x' as numeric);
            _lesson_name = _lesson ::jsonb ->> 'subject1';
            _cabinet_name = _lesson ::jsonb ->> 'subject2';
            _teacher_name = _lesson ::jsonb ->> 'subject3';
            _lesson_type = _lesson ::jsonb ->> 'lessontype';

            if
                            _group ::jsonb ->> 'id' in (select group_id from groups) and
                            _cabinet_name notnull and
                            _cabinet_name not in ('ДОТ', 'СДО', 'Организация', 'Спортивный корпус', 'Гл.', 'Гл - ')
            then

                select cabinet_id
                from cabinets
                where cabinets.cabinet_name = _cabinet_name
                into _cabinet_id;

                select user_id
                from users
                where user_name = _teacher_name
                into _teacher_id;

                if
                    _lesson_number = 1 then
                    _lesson_time_start = '08:30:00';
                    _lesson_time_end = '10:00:00';
                end if;
                if
                    _lesson_number = 2 then
                    _lesson_time_start = '10:10:00';
                    _lesson_time_end = '11:40:00';
                end if;
                if
                    _lesson_number = 3 then
                    _lesson_time_start = '11:50:00';
                    _lesson_time_end = '13:20:00';
                end if;
                if
                    _lesson_number = 4 then
                    _lesson_time_start := '14:00:00';
                    _lesson_time_end := '15:30:00';
                end if;
                if
                    _lesson_number = 5 then
                    _lesson_time_start := '15:40:00';
                    _lesson_time_end := '17:10:00';
                end if;
                if
                    _lesson_number = 6 then
                    _lesson_time_start := '17:20:00';
                    _lesson_time_end := '18:50:00';
                end if;
                if
                    _lesson_number = 7 then
                    _lesson_time_start := '19:00:00';
                    _lesson_time_end := '20:30:00';
                end if;

                insert into timetable (lesson_id,
                                       lesson_name,
                                       lesson_day,
                                       lesson_number,
                                       lesson_type,
                                       lesson_time_start,
                                       lesson_time_end,
                                       cabinet_id,
                                       teacher_id,
                                       group_id,
                                       subgroup)

                values (nextval('timetable_sequence'),
                        _lesson_name,
                        _lesson_day,
                        _lesson_number,
                        _lesson_type,
                        _lesson_time_start,
                        _lesson_time_end,
                        _cabinet_id,
                        _teacher_id,
                        _group ::jsonb ->> 'id',
                        _lesson ::jsonb ->> 'subgroup');
            end if;
        end loop;
end;
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


create or replace function get_cabinet_for_activity(_cabinetId varchar, _userId varchar) returns varchar
    language plpgsql
as
$$
declare
    _lessons         timetable;
    _current_lesson  timetable;
    _next_lesson     timetable;
    _time_difference time;
begin
    SET TIMEZONE = 'EUROPE/MOSCOW';
    select *
    from timetable
    where cabinet_id = _cabinetId
    into _lessons;

    if _lessons is not null then --Если сегодня есть пары в кабинете

        select *
        from get_current_lesson_in_cabinet(_cabinetId)
        into _current_lesson;

        if _current_lesson.teacher_id = _userId then

            call write_log(_cabinetId, _userId, 'Аудитория занята');
            return 'Вы успешно успешно взяли аудиторию';

        else

            select *
            from get_next_lesson_in_cabinet(_cabinetId, _current_lesson.lesson_number + 1)
            into _next_lesson;

            if _next_lesson is not null then

                select extract(epoch from (cast(_next_lesson.lesson_time_start as time) - localtime) / 60)
                into _time_difference;

                if _time_difference >= 55 then
                    call write_log(_cabinetId, _userId, 'Аудитория занята');
                    return 'Вы успешно успешно взяли аудиторию';
                else
                    return 'Вы не можете взять аудиторию, до начала следующего занятия меньше 55 минут';
                end if;

            else
                call write_log(_cabinetId, _userId, 'Аудитория занята');
                return 'Вы успешно успешно взяли аудиторию';
            end if;
        end if;

    else --Если сегодня пар в кабинете нет
        call write_log(_cabinetId, _userId, 'Аудитория занята');
        return 'Вы успешно успешно взяли аудиторию';
    end if;


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
    from timetable
    where lesson_day = EXTRACT(ISODOW FROM localtimestamp)
      and cast(localtime as varchar) between lesson_time_start and lesson_time_end
      and cabinet_id = _cabinetId
    into lesson;

    return lesson;
end;
$$;

create or replace function get_next_lesson_in_cabinet(_cabinetId varchar, _lesson_number integer) returns timetable
    language plpgsql
as
$$
declare
    _next_lesson timetable;
begin
    select *
    from timetable
    where cabinet_id = _cabinetId
      and lesson_number = _lesson_number
    into _next_lesson;
end;
$$;

create sequence if not exists logs_sequence
    increment 1
    start 1;

create sequence if not exists timetable_sequence
    increment 1
    start 1;