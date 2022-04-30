alter table apibox_apix drop request_type;
alter table apibox_apix drop path;
alter TABLE apibox_api_http add  request_type VARCHAR(32) NOT NULL;
alter TABLE apibox_api_http add  path VARCHAR(256) NOT NULL;

