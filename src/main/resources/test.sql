-- Показать всех Advertiser'ов для Publisher'а
SELECT advertisers.id, advertisers.name, advertisers.commission
FROM pub_adv
         INNER JOIN advertisers on pub_adv.adv_id = advertisers.id
WHERE pub_adv.pub_id = 100004;

-- Показать все Продажи(Sales) для Publisher'а
SELECT sales.date_time, offer.name, offer.price
FROM sales
         INNER JOIN offer on sales.offer_id = offer.id
WHERE pub_id = 100004;

-- Показать сумму Продаж(Sales) по каждому товару для Publisher'а
SELECT sum(offer.price), offer.name
FROM sales
         INNER JOIN offer on sales.offer_id = offer.id
WHERE pub_id = 100004
GROUP BY offer.name;

SELECT publishers.name, advertisers.name, offer.name, sum(offer.price), advertisers.commission
FROM sales
         INNER JOIN offer on sales.offer_id = offer.id
         INNER JOIN advertisers on offer.adv_id = advertisers.id
         INNER JOIN pub_adv on advertisers.id = pub_adv.adv_id
         INNER JOIN publishers on pub_adv.pub_id = publishers.id
WHERE publishers.id = 100004
GROUP BY offer.name, publishers.name, advertisers.name, advertisers.commission;