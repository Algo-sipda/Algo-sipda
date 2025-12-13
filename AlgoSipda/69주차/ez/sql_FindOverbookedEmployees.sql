WITH hours_weeks AS (
    SELECT m.employee_id, e.employee_name, e.department, SUM(m.duration_hours) AS total_hours
    FROM meetings m
    JOIN employees e USING (employee_id)
    GROUP BY m.employee_id, e.employee_name, e.department, YEARWEEK(meeting_date, 1)
)

SELECT employee_id, employee_name, department, COUNT(*) AS meeting_heavy_weeks
FROM hours_weeks
WHERE total_hours > 20
GROUP BY employee_id, employee_name, department
HAVING COUNT(*) >= 2
ORDER BY meeting_heavy_weeks DESC, employee_name