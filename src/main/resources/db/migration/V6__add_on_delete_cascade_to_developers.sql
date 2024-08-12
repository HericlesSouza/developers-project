ALTER TABLE developers DROP CONSTRAINT fk_developer_infos;

ALTER TABLE developers
ADD CONSTRAINT fk_developer_infos FOREIGN KEY ("developer_infos_id") REFERENCES developer_infos("id") ON DELETE CASCADE;
