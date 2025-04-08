SELECT c.name
FROM companies c
JOIN games g ON c.company_id = g.publisher_id
GROUP BY c.company_id
HAVING COUNT(g.game_id) >= 10;
