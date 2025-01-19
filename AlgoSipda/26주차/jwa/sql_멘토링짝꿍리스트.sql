-- 멘티, 멘토 각각 필터링
-- 조인 (다른 부서끼리)

WITH MENTEE AS (
  SELECT *
  FROM employees
  WHERE join_date BETWEEN '2021-10-01' AND '2021-12-31'
),
MENTOR AS (
  SELECT *
  FROM employees
  WHERE join_date <= '2019-12-31'
)

SELECT E.employee_id AS mentee_id, E.name AS mentee_name,
  O.employee_id AS mentor_id, O.name AS mentor_name
FROM MENTEE E
LEFT JOIN MENTOR O ON E.department != O.department
ORDER BY mentee_id, mentor_id