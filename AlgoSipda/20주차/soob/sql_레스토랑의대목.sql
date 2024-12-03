SELECT *
FROM tips
WHERE day IN (
  SELECT day
  FROM tips
  GROUP BY day
  HAVING SUM(total_bill + tip) >= 1500
)