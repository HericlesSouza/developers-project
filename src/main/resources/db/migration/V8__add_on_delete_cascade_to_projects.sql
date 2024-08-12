ALTER TABLE projects DROP CONSTRAINT fk_developer;

ALTER TABLE projects
ADD CONSTRAINT fk_developer FOREIGN KEY ("developer_id") REFERENCES developers("id") ON DELETE CASCADE;
