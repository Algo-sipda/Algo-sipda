-- [SQL] find-students-who-improved
-- https://leetcode.com/problems/find-students-who-improved/description/
-- 가장 처음 테이블 (최대, 최소 날짜) -> 이값에 해당하는 최소날짜의 socre, 최대 날짜의 score 비교
SELECT
  s_min.student_id,
  s_min.subject,
  s_min.score  AS first_score,
  s_max.score  AS latest_score
FROM (
  SELECT student_id, subject,
         MIN(exam_date) AS min_date,
         MAX(exam_date) AS max_date
  FROM Scores
  WHERE exam_date IS NOT NULL
  GROUP BY student_id, subject
) g
JOIN Scores s_min
  ON s_min.student_id = g.student_id
 AND s_min.subject    = g.subject
 AND s_min.exam_date  = g.min_date
JOIN Scores s_max
  ON s_max.student_id = g.student_id
 AND s_max.subject    = g.subject
 AND s_max.exam_date  = g.max_date
WHERE s_min.exam_date < s_max.exam_date
  AND s_min.score < s_max.score
ORDER BY s_min.student_id, s_min.subject;
