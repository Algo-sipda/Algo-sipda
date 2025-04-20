SELECT bike_id FROM rental_history 
WHERE rent_at >= '2021-01-01 00:00:00' 
AND rent_at <= '2021-01-31 23:59:59'
GROUP BY bike_id
HAVING SUM(distance) >= 50000;