CREATE SCHEMA posting;

CREATE TABLE posting.post (
id BIGSERIAL PRIMARY KEY,
uuid VARCHAR(36),
version BIGINT,
post VARCHAR(255)
);

CREATE TABLE posting.tag(
id BIGSERIAL PRIMARY KEY,
uuid VARCHAR(36),
name VARCHAR(255)
);

CREATE TABLE posting.post_tag(
post_id BIGINT REFERENCES posting.post,
uuid VARCHAR(36),
tag_id BIGINT REFERENCES posting.tag,
PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE posting.message (
id BIGSERIAL PRIMARY KEY,
uuid VARCHAR(36),
content VARCHAR(255)
);

CREATE TABLE posting.message_tag(
message_id BIGINT REFERENCES posting.message,
uuid VARCHAR(36),
tag_id BIGINT REFERENCES posting.tag,
PRIMARY KEY (message_id, tag_id)
);