SELECT
  s.user_id,
  ROUND(IFNULL(AVG(c.action = 'confirmed'), 0), 2) AS confirmation_rate
FROM Signups s
LEFT JOIN Confirmations c
  ON c.user_id = s.user_id
GROUP BY s.user_id;
