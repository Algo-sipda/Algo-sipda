WITH agg AS (
  SELECT user_id, activity_type, ROUND(AVG(activity_duration), 2) AS avg_dur
  FROM UserActivity
  WHERE activity_type IN ('free_trial', 'paid')
  GROUP BY user_id, activity_type
)
SELECT f.user_id,
       f.avg_dur AS trial_avg_duration,
       p.avg_dur AS paid_avg_duration
FROM agg f
JOIN agg p
  ON f.user_id = p.user_id
WHERE f.activity_type = 'free_trial'
  AND p.activity_type = 'paid'
ORDER BY f.user_id;
