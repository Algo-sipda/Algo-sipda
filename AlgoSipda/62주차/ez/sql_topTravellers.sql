SELECT name, IFNULL(travelled_distance, 0) travelled_distance
FROM (
    SELECT user_id, SUM(distance) travelled_distance
    FROM Rides
    GROUP BY user_id
) R RIGHT JOIN Users U ON R.user_id = U.id
ORDER BY travelled_distance DESC, name;
