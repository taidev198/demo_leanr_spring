CREATE TABLE IF NOT EXISTS Run
(
    id
    INT
    not
    null,
    title
    varchar
(
    30
) not null,
    started_on timestamp not null,
    completed_time timestamp not null,
    miles int not null,
    location varchar
(
    30
) not null,
    PRIMARY KEY
(
    id
)
    );

