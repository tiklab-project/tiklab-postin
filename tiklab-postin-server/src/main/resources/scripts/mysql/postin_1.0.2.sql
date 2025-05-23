DROP TABLE postin_db_backups;

ALTER TABLE postin_apix ADD COLUMN path VARCHAR(256);

UPDATE postin_apix
SET path = (SELECT path FROM postin_api_http WHERE postin_api_http.id = postin_apix.id);

ALTER TABLE postin_api_http DROP COLUMN path;

ALTER TABLE postin_http_request_header RENAME TO postin_api_request_header;
ALTER TABLE postin_api_request_header RENAME COLUMN http_id TO api_id;

ALTER TABLE postin_http_request_query RENAME TO postin_api_request_query;
ALTER TABLE postin_api_request_query RENAME COLUMN http_id TO api_id;

ALTER TABLE postin_http_request_json RENAME TO postin_api_request_json;
ALTER TABLE postin_api_request_json RENAME COLUMN http_id TO api_id;
ALTER TABLE postin_api_request_json
DROP COLUMN parent_id,
DROP COLUMN param_name,
DROP COLUMN data_type,
DROP COLUMN required,
DROP COLUMN value,
DROP COLUMN sort;

ALTER TABLE postin_http_request_raw RENAME TO postin_api_request_raw;
ALTER TABLE postin_api_request_raw RENAME COLUMN http_id TO api_id;

ALTER TABLE postin_http_request RENAME TO postin_api_request;
ALTER TABLE postin_api_request RENAME COLUMN http_id TO api_id;






