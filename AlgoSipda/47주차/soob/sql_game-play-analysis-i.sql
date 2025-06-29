SELECT player_id, min(event_date) as first_login
FROM activity
GROUP BY player_id