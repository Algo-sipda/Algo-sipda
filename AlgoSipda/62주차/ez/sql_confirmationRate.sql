SELECT DISTINCT S.user_id,
    IFNULL(ROUND((SUM(CASE WHEN action = "confirmed" THEN 1 ELSE 0 END) OVER(PARTITION BY user_id) / COUNT(action) OVER(PARTITION BY user_id)), 2), 0) AS confirmation_rate
FROM Confirmations C RIGHT JOIN Signups S ON C.user_id = S.user_id;