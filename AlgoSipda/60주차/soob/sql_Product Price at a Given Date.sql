WITH latest AS (
  SELECT
    product_id,
    new_price,
    ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY change_date DESC) AS rn
  FROM Products
  WHERE change_date <= '2019-08-16'
),
pid AS (
  SELECT DISTINCT product_id FROM Products
)
SELECT
  p.product_id,
  COALESCE(l.new_price, 10) AS price
FROM pid p
LEFT JOIN latest l
  ON l.product_id = p.product_id AND l.rn = 1;
