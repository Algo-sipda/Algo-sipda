SELECT DISTINCT r.athlete_id
FROM records r
JOIN events e ON r.event_id = e.id
WHERE e.sport = 'Golf';
