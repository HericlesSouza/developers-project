CREATE TABLE project_technologies (
    "id" SERIAL PRIMARY KEY,
    "project_id" INTEGER NOT NULL,
    "technology_id" INTEGER NOT NULL,
    CONSTRAINT fk_project FOREIGN KEY ("project_id") REFERENCES projects("id"),
    CONSTRAINT fk_technology FOREIGN KEY ("technology_id") REFERENCES technologies("id"),
    UNIQUE ("project_id", "technology_id")
);
