SELECT user_id,
    ROUND(AVG(
        CASE WHEN activity_type = 'free_trial' then activity_duration
        end
    ), 2) trial_avg_duration,
    ROUND(AVG(
        CASE WHEN activity_type = 'paid' then activity_duration
        end
    ), 2) paid_avg_duration
FROM UserActivity
where user_id in (select user_id from useractivity where activity_type = 'paid')
group by user_id