WITH Ranked AS (
  SELECT
    student_id,
    subject,
    score,
    exam_date,
    RANK() OVER (PARTITION BY student_id, subject ORDER BY exam_date) AS r1,
    RANK() OVER (PARTITION BY student_id, subject ORDER BY exam_date DESC) AS r2
  FROM Scores
),
FirstLast AS (
  SELECT
    student_id,
    subject,
    MIN(CASE WHEN r1 = 1 THEN score END) AS first_score,
    MAX(CASE WHEN r2 = 1 THEN score END) AS latest_score
  FROM Ranked
  GROUP BY student_id, subject
  HAVING COUNT(*) > 1
)
SELECT student_id, subject, first_score, latest_score
FROM FirstLast
WHERE latest_score > first_score;