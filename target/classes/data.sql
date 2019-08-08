DELETE
FROM pub_adv;
DELETE
FROM sales;
DELETE
FROM offers;
DELETE
FROM advertisers;
DELETE
FROM publishers;
DELETE
FROM resp_detail;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO resp_detail (name, transaction_number, date_time, offer_name, offer_number, publisher_name)
VALUES ('Standard', 'transactionNumberName', 'dateTimeName', 'offerName', 'offerNumberName', 'publisherName');
INSERT INTO resp_detail (name, transaction_number, date_time, offer_name, offer_number, publisher_name)
VALUES ('Custom Resp for 300 Volt', 'transactionNumberName', 'dateTimeName', 'offerName', 'offerNumberName', 'publisherName');

INSERT INTO advertisers (name, statistic_url, commission, resp_detail_id)
VALUES ('Bio Add Pro', 'https://biopro.com/bio-statistics', 10, 100000);
INSERT INTO advertisers (name, statistic_url, commission, resp_detail_id)
VALUES ('300 Volt', 'https://300V.com/statistics',15, 100001);

INSERT INTO publishers (name)
VALUES ('Publisher1');
INSERT INTO publishers (name)
VALUES ('Publisher2');
INSERT INTO publishers (name)
VALUES ('Publisher3');

INSERT INTO pub_adv (pub_id, adv_id)
VALUES (100004, 100002);
INSERT INTO pub_adv (pub_id, adv_id)
VALUES (100005, 100002);
INSERT INTO pub_adv (pub_id, adv_id)
VALUES (100006, 100002);
INSERT INTO pub_adv (pub_id, adv_id)
VALUES (100006, 100003);

INSERT INTO offers (offer_number, name, price, adv_id)
VALUES ('360305743', 'California Gold Nutrition, BCAA, (454 g)', 1260, 100002);
INSERT INTO offers (offer_number, name, price, adv_id)
VALUES ('580485485', 'Now Foods, Dopa Mucuna, 90 Veg Capsules', 700, 100002);
INSERT INTO offers (offer_number, name, price, adv_id)
VALUES ('220301555', 'Цепная пила PATRIOT ESP 1612', 3029, 100003);
INSERT INTO offers (offer_number, name, price, adv_id)
VALUES ('230382047', 'Электрический степлер ЗУБР ЗСП-2000', 4570, 100003);

INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('212564789547', '2019-08-05 10:32:47', 100007, 100004);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('347864434644', '2019-08-04 19:21:12', 100007, 100004);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('336247878951', '2019-08-06 12:56:04', 100007, 100005);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('156785156872', '2019-08-03 02:08:59', 100007, 100006);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('754797448724', '2019-08-01 13:47:25', 100008, 100004);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('156785533341', '2019-08-02 06:10:41', 100008, 100005);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('465835744534', '2019-08-02 08:11:30', 100008, 100005);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('236156852463', '2019-08-03 11:52:01', 100008, 100006);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('436978566452', '2019-08-05 14:23:26', 100009, 100006);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('216385864563', '2019-08-06 03:14:58', 100009, 100006);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('379088654864', '2019-08-03 04:58:17', 100010, 100004);
INSERT INTO sales (transaction_number, date_time, offer_id, pub_id)
VALUES ('354538845551', '2019-08-06 22:52:34', 100010, 100004);