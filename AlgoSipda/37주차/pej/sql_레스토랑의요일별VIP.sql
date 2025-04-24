SELECT t.total_bill, t.tip, t.sex, t.smoker, t.day, t.time, t.size 
FROM tips t
JOIN (
  SELECT day, max(total_bill) as max_total_bil
  FROM tips 
  GROUP BY day
) as m 
ON t.day = m.day AND t.total_bill = m.max_total_bil;


-- SELECT r.total_bill, r.tip, r.sex, r.smoker, r.day, r.time, r.size
-- FROM (
--   SELECT *, 
--   RANK() OVER (PARTITION BY day ORDER BY total_bill DESC) as rnk
--   FROM tips
-- ) as r
-- WHERE rnk = 1;