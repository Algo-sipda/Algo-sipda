SELECT 
  u.user_id,
  (COUNT(e.user_a_id) + COUNT(e.user_b_id)) / 2 AS num_friends
FROM users u
LEFT JOIN edges e 
  ON u.user_id = e.user_a_id OR u.user_id = e.user_b_id
GROUP BY u.user_id
ORDER BY num_friends desc, u.user_id;
