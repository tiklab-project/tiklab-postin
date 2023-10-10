DROP TABLE postin_db_backups;

ALTER TABLE postin_apix
ADD COLUMN path VARCHAR(256);

UPDATE postin_apix
SET path = (SELECT path FROM postin_api_http WHERE postin_api_http.id = postin_apix.id);

ALTER TABLE postin_api_http
DROP COLUMN path;