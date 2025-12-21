SELECT machine_id,
       ROUND(AVG(end_time - start_time), 3) AS processing_time
FROM (
    SELECT machine_id,
           process_id,
           MAX(IF(activity_type = 'end', timestamp, NULL)) AS end_time,
           MAX(IF(activity_type = 'start', timestamp, NULL)) AS start_time
    FROM Activity
    GROUP BY machine_id, process_id
) t
GROUP BY machine_id;
