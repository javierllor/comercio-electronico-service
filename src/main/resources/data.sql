CREATE TABLE IF NOT EXISTS PRICES
(
    brand_id
    INT,
    start_date
    TIMESTAMP,
    end_date
    TIMESTAMP,
    price_list
    INT,
    product_id
    BIGINT,
    priority
    INT,
    price
    FLOAT,
    currency
    VARCHAR
(
    10
)
    );

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 35455, 0, 35.50, 'EUR');

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 2, 35455, 1, 25.45, 'EUR');

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 3, 35455, 1, 30.50, 'EUR');

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 4, 35455, 1, 38.95, 'EUR');
