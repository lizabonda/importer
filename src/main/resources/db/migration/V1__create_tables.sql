CREATE TABLE municipality
(
    id   UUID         PRIMARY KEY,
    code BIGINT       NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE municipality_part
(
    id              UUID         PRIMARY KEY,
    code            BIGINT       NOT NULL UNIQUE,
    name            VARCHAR(255) NOT NULL,
    municipality_code BIGINT         NOT NULL  REFERENCES municipality(code) ON DELETE CASCADE
);