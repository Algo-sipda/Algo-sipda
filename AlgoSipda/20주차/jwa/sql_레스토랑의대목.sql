WITH
  DaySales AS (
    SELECT
      day,
      SUM(total_bill) AS total_sales
    FROM
      tips
    GROUP BY
      day
  )
SELECT
  t.*
FROM
  tips t
  JOIN DaySales ds ON t.day = ds.day
WHERE
  ds.total_sales >= 1500;
