SELECT e.employee_id,
       CASE
           WHEN a.cnt = 1 THEN e.department_id
           ELSE MAX(CASE WHEN e.primary_flag = 'Y' THEN e.department_id END)
       END AS department_id
FROM employee e
JOIN (
    SELECT employee_id, COUNT(*) AS cnt
    FROM employee
    GROUP BY employee_id
) a ON e.employee_id = a.employee_id
GROUP BY e.employee_id, a.cnt;
