-- [SQL] average-time-of-process-per-machine
-- https://leetcode.com/problems/average-time-of-process-per-machine/description/

select machine_id, ROUND(SUM(CASE WHEN activity_type='start' THEN timestamp*-1 ELSE timestamp END)*1.0
    / (SELECT COUNT(DISTINCT process_id)),3) AS processing_time
from activity
group by machine_id;