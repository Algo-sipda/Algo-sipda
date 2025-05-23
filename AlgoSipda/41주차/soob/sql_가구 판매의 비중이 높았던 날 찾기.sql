SELECT
  order_date,
  furniture_cnt AS furniture,
  ROUND(furniture_cnt * 100.0 / total_cnt, 2) AS furniture_pct
FROM (
  SELECT
    order_date,
    COUNT(DISTINCT order_id) AS total_cnt,
    COUNT(DISTINCT CASE WHEN category = 'Furniture' THEN order_id END) AS furniture_cnt
  FROM records
  GROUP BY order_date
)
WHERE total_cnt >= 10
  AND furniture_cnt * 100.0 / total_cnt >= 40
ORDER BY furniture_pct DESC, order_date;
