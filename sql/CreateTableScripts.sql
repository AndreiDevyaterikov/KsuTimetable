create table if not exists buildings(
                          build_id VARCHAR PRIMARY KEY,
                          build_name VARCHAR NOT NULL
);

create table if not exists cabinets(
    cabinet_id VARCHAR PRIMARY KEY,
    cabinet_name VARCHAR NOT NULL,
    build_id VARCHAR (25),
    constraint FK_build_id FOREIGN KEY (build_id) REFERENCES buildings(build_id)
);

create table if not exists faculties(
    faculty_id VARCHAR (25) PRIMARY KEY,
    faculty_name VARCHAR NOT NULL
);

create table if not exists directions(
    direction_id VARCHAR (25) PRIMARY KEY,
    direction_name VARCHAR NOT NULL,
    faculty_id VARCHAR,
    CONSTRAINT FK_faculty_id FOREIGN KEY (faculty_id) REFERENCES faculties(faculty_id)
);

create table if not exists groups(
    group_id varchar primary key,
    group_name varchar not null,
    direction_id varchar,
    constraint FK_direction_id foreign key (direction_id) references directions(direction_id)
);