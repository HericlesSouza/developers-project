ALTER TABLE project_technologies DROP CONSTRAINT fk_project;
ALTER TABLE project_technologies DROP CONSTRAINT fk_technology;

ALTER TABLE project_technologies
ADD CONSTRAINT fk_project FOREIGN KEY ("project_id") REFERENCES projects("id") ON DELETE CASCADE,
ADD CONSTRAINT fk_technology FOREIGN KEY ("technology_id") REFERENCES technologies("id") ON DELETE CASCADE;
