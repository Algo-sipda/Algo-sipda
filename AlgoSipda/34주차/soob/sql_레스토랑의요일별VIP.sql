SELECT *
FROM tips
GROUP BY day
HAVING total_bill = MAX(total_bill);