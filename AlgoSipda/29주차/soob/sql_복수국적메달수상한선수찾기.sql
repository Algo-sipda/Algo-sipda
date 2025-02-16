SELECT DISTINCT athletes.name
FROM records
JOIN games ON records.game_id = games.id
JOIN athletes ON records.athlete_id = athletes.id
WHERE games.year >= 2000
  AND records.medal IS NOT NULL
GROUP BY records.athlete_id, athletes.name
HAVING COUNT(DISTINCT records.team_id) > 1
ORDER BY athletes.name;
