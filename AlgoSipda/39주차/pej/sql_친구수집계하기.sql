-- [SQL] 친구 수 집계하기 https://solvesql.com/proulems/number-of-friends/
-- LEFT JOIN 해서 친구가 없는 사람들은 친구가 0명으로 표시해야 함 
-- FROM 절의 서브쿼리 >> with 절로 대체 가능 
-- UNION ALL : 중복 포함해서 테이블 합치기 

SELECT u.user_id, IFNULL(SUM(combined.cnt), 0) as num_friends 
FROM users u LEFT JOIN 
(
  SELECT user_a_id as user_id, count(user_b_id) as cnt FROM edges 
  GROUP BY user_a_id
  UNION ALL
  SELECT user_b_id as user_id, count(user_a_id) as cnt FROM edges
  GROUP BY user_b_id
) as combined
ON u.user_id = combined.user_id
GROUP BY u.user_id
ORDER BY num_friends DESC, u.user_id ASC;