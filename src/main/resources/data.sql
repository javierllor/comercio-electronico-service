CREATE TABLE IF NOT EXISTS PRICES
(
    brand_id
    INT,
    start_date
    VARCHAR
(
    20
),
    end_date
    VARCHAR
(
    20
),
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
VALUES (1, '2020-06-14-00.00.00', '2020-12-31-23.59.59', 1, 35455, 0, 35.50, 'EUR');

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-14-15.00.00', '2020-06-14-18.30.00', 2, 35455, 1, 25.45, 'EUR');

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-15-00.00.00', '2020-06-15-11.00.00', 3, 35455, 1, 30.50, 'EUR');

INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (1, '2020-06-15-16.00.00', '2020-12-31-23.59.59', 4, 35455, 1, 38.95, 'EUR');
