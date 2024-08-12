CREATE TABLE developers (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "email" VARCHAR(50) NOT NULL UNIQUE,
    "developer_infos_id" INTEGER UNIQUE,
    CONSTRAINT fk_developer_infos FOREIGN KEY ("developer_infos_id") REFERENCES developer_infos("id")
);
