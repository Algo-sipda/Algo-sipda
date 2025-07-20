SELECT actor_id, director_id
FROM (
    SELECT actor_id, director_id, COUNT(*) as cooperation
    FROM actordirector
    GROUP BY actor_id, director_id
) as coop
WHERE coop.cooperation >= 3