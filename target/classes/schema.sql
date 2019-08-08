DROP TABLE IF EXISTS pub_adv;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS offers;
DROP TABLE IF EXISTS advertisers;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS resp_detail;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE resp_detail
(
    id                 INTEGER DEFAULT nextval('global_seq'),
    name               VARCHAR NOT NULL UNIQUE,
    transaction_number VARCHAR NOT NULL,
    date_time          VARCHAR NOT NULL,
    offer_name         VARCHAR NOT NULL,
    offer_number       VARCHAR NOT NULL,
    publisher_name     VARCHAR NOT NULL,
    UNIQUE (name, transaction_number, date_time,
            offer_name, offer_number, publisher_name),
    PRIMARY KEY (id)
);

CREATE TABLE advertisers
(
    id             INTEGER DEFAULT nextval('global_seq'),
    name           VARCHAR  NOT NULL UNIQUE,
    statistic_url  VARCHAR  NOT NULL UNIQUE,
    commission     SMALLINT NOT NULL CHECK (commission >= 0 AND commission <= 100),
    resp_detail_id INTEGER  NOT NULL,
    FOREIGN KEY (resp_detail_id) REFERENCES resp_detail (id) ON DELETE RESTRICT,
    PRIMARY KEY (id)
);

CREATE TABLE publishers
(
    id   INTEGER DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE pub_adv
(
    pub_id INTEGER,
    adv_id INTEGER,
    UNIQUE (pub_id, adv_id),
    FOREIGN KEY (pub_id) REFERENCES publishers (id) ON DELETE CASCADE,
    FOREIGN KEY (adv_id) REFERENCES advertisers (id) ON DELETE CASCADE
);

CREATE TABLE offers
(
    id           INTEGER DEFAULT nextval('global_seq'),
    offer_number VARCHAR NOT NULL,
    name         VARCHAR NOT NULL,
    price        INTEGER NOT NULL,
    adv_id       INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (adv_id) REFERENCES advertisers (id) ON DELETE CASCADE
);

CREATE TABLE sales
(
    id                 INTEGER DEFAULT nextval('global_seq'),
    transaction_number VARCHAR   NOT NULL,
    date_time          TIMESTAMP NOT NULL,
    offer_id           INTEGER   NOT NULL,
    pub_id             INTEGER   NOT NULL,
    UNIQUE (transaction_number, offer_id),
    PRIMARY KEY (id),
    FOREIGN KEY (offer_id) REFERENCES offers (id) ON DELETE CASCADE,
    FOREIGN KEY (pub_id) REFERENCES publishers (id) ON DELETE CASCADE
);