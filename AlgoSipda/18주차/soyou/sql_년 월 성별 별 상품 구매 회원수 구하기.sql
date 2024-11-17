SELECT YEAR(sales_date) as year, MONTH(sales_date) as month, gender, COUNT(DISTINCT o.user_id) as users
FROM ONLINE_SALE as o
    INNER JOIN USER_INFO as u
ON o.user_id = u.user_id
WHERE gender IS NOT NULL
GROUP BY year, month, gender
ORDER BY year, month, gender;