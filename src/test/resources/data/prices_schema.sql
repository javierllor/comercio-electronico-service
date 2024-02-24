CREATE TABLE IF NOT EXISTS PRICES
(
    brand_id   INT,
    start_date TIMESTAMP,
    end_date   TIMESTAMP,
    price_list INT,
    product_id BIGINT,
    priority   INT,
    price      FLOAT,
    currency   VARCHAR(10)
);
