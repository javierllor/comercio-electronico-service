CREATE TABLE IF NOT EXISTS PRICES
(
    brand_id   INT,
    start_date VARCHAR(20),
    end_date   VARCHAR(20),
    price_list INT,
    product_id BIGINT,
    priority   INT,
    price      FLOAT,
    currency   VARCHAR(10)
);
