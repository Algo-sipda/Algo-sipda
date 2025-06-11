--[SQL] Rank Scores https://leetcode.com/problems/rank-scores/

SELECT 
  ROUND(score, 2) AS score,
  DENSE_RANK() OVER (ORDER BY score DESC) AS `rank`
FROM Scores;
