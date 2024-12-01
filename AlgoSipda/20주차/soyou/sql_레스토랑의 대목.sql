SELECT *
FROM tips
WHERE day in (SELECT day
    FROM tips
    GROUP BY day
    HAVING SUM(total_bill) >= 1500);