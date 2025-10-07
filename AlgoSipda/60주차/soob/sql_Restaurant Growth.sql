WITH d AS (
  SELECT visited_on, SUM(amount) AS amount
  FROM Customer
  GROUP BY visited_on
),
w AS (
  SELECT
    visited_on,
    SUM(amount) OVER (ORDER BY visited_on ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS amount,
    ROW_NUMBER() OVER (ORDER BY visited_on) AS rn
  FROM d
)
SELECT
  visited_on,
  amount,
  ROUND(amount / 7, 2) AS average_amount
FROM w
WHERE rn >= 7
ORDER BY visited_on;