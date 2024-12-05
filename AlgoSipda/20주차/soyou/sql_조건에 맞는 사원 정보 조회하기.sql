SELECT s.score, s.emp_no, e.emp_name, e.position, e.email
FROM (SELECT emp_no, SUM(score) as score
      FROM HR_GRADE
      GROUP BY emp_no
      ORDER BY score DESC
      limit 1) as s
JOIN HR_EMPLOYEES as e
ON s.emp_no = e.emp_no