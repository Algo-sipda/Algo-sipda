SELECT employee_id
FROM Employees e
LEFT JOIN Salaries s USING (employee_id)
WHERE s.salary IS NULL

UNION

SELECT employee_id
FROM Salaries s
LEFT JOIN Employees e USING (employee_id)
WHERE e.name IS NULL

ORDER BY employee_id;
