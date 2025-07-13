WITH paired AS (
    SELECT 
        a.machine_id,
        a.process_id,
        MAX(CASE WHEN a.activity_type = 'start' THEN a.timestamp END) AS start_time,
        MAX(CASE WHEN a.activity_type = 'end' THEN a.timestamp END) AS end_time
    FROM Activity a
    GROUP BY a.machine_id, a.process_id
),
durations AS (
    SELECT 
        machine_id,
        end_time - start_time AS duration
    FROM paired
)
SELECT 
    machine_id,
    ROUND(AVG(duration), 3) AS processing_time
FROM durations
GROUP BY machine_id
ORDER BY machine_id;