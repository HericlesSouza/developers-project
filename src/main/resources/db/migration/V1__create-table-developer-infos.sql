CREATE TYPE os_enum AS ENUM ('Windows', 'Linux', 'MacOS');

CREATE TABLE developer_infos (
    "id" SERIAL PRIMARY KEY,
    "developer_since" DATE NOT NULL,
    "preferred_os" os_enum NOT NULL
);