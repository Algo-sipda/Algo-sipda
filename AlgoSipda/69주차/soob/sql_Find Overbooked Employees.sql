WITH weekly_hours AS (
    SELECT
        m.employee_id,
        e.employee_name,
        e.department,
        YEARWEEK(m.meeting_date, 1) AS week_id,
        SUM(m.duration_hours) AS total_hours
    FROM meetings m
    JOIN employees e
        ON m.employee_id = e.employee_id
    GROUP BY
        m.employee_id,
        e.employee_name,
        e.department,
        YEARWEEK(m.meeting_date, 1)
),
meeting_heavy_weeks AS (
    SELECT
        employee_id,
        employee_name,
        department,
        week_id
    FROM weekly_hours
    WHERE total_hours > 20
)
SELECT
    employee_id,
    employee_name,
    department,
    COUNT(*) AS meeting_heavy_weeks
FROM meeting_heavy_weeks
GROUP BY
    employee_id,
    employee_name,
    department
HAVING
    COUNT(*) >= 2
ORDER BY
    meeting_heavy_weeks DESC,
    employee_name ASC;
