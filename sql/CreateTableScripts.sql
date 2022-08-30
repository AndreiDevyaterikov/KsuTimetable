create table if not exists buildings
(
    building_id   VARCHAR PRIMARY KEY,
    building_name VARCHAR NOT NULL
);

create table if not exists cabinets
(
    cabinet_id   VARCHAR PRIMARY KEY,
    cabinet_name VARCHAR NOT NULL,
    building_id  VARCHAR(25),
    constraint FK_build_id FOREIGN KEY (building_id) REFERENCES buildings (building_id)
);

create table if not exists faculties
(
    faculty_id   VARCHAR(25) PRIMARY KEY,
    faculty_name VARCHAR NOT NULL
);

create table if not exists directions
(
    direction_id   VARCHAR(25) PRIMARY KEY,
    direction_name VARCHAR NOT NULL,
    faculty_id     VARCHAR,
    CONSTRAINT FK_faculty_id FOREIGN KEY (faculty_id) REFERENCES faculties (faculty_id)
);

create table if not exists groups
(
    group_id     varchar primary key,
    group_name   varchar not null,
    direction_id varchar,
    constraint FK_direction_id foreign key (direction_id) references directions (direction_id)
);

create table if not exists users
(
    user_id   varchar primary key,
    user_name varchar not null
);

create table if not exists timetable
(
    lesson_id         varchar primary key,
    lesson_name       varchar not null,
    lesson_day        integer not null,
    lesson_number     integer not null,
    lesson_type       varchar not null,
    lesson_time_start varchar not null,
    lesson_time_end   varchar not null,
    cabinet_id        varchar,
    teacher_id        varchar,
    group_id          varchar not null,
    type_week         varchar,
    subgroup          varchar,

    constraint FK_cabinet_id foreign key (cabinet_id) references cabinets (cabinet_id),
    constraint FK_teacher_id foreign key (teacher_id) references users (user_id),
    constraint FK_group_id foreign key (group_id) references groups (group_id)
);

create table if not exists logbook
(
    log_id       integer primary key,
    status       varchar   not null,
    time_changed timestamp not null,
    week_day     integer   not null,
    cabinet_id   varchar,
    teacher_id   varchar,

    constraint FK_cabinet_id foreign key (cabinet_id) references cabinets (cabinet_id),
    constraint FK_teacher_id foreign key (teacher_id) references users (user_id)
)
