SELECT
  d.name  AS Department,
  e.name  AS Employee,
  e.salary AS Salary
FROM Employee e
JOIN (
  SELECT departmentId, MAX(salary) AS max_salary
  FROM Employee
  GROUP BY departmentId
) m
  ON e.departmentId = m.departmentId
 AND e.salary = m.max_salary
JOIN Department d
  ON d.id = e.departmentId;
