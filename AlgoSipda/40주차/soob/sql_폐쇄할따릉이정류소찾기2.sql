WITH usage AS (
    SELECT
        station_id,
        SUM(CASE WHEN strftime('%Y-%m', rent_at) = '2018-10' THEN 1 ELSE 0 END +
            CASE WHEN strftime('%Y-%m', return_at) = '2018-10' THEN 1 ELSE 0 END) AS cnt_2018,
        SUM(CASE WHEN strftime('%Y-%m', rent_at) = '2019-10' THEN 1 ELSE 0 END +
            CASE WHEN strftime('%Y-%m', return_at) = '2019-10' THEN 1 ELSE 0 END) AS cnt_2019
    FROM (
        SELECT rent_station_id AS station_id, rent_at, NULL AS return_at FROM rental_history
        UNION ALL
        SELECT return_station_id AS station_id, NULL AS rent_at, return_at FROM rental_history
    )
    GROUP BY station_id
)
SELECT s.station_id, s.name, s.local,
       ROUND(u.cnt_2019 * 100.0 / u.cnt_2018, 2) AS usage_pct
FROM usage u
JOIN station s ON u.station_id = s.station_id
WHERE u.cnt_2018 > 0 AND u.cnt_2019 > 0 AND u.cnt_2019 * 100.0 / u.cnt_2018 <= 50
ORDER BY usage_pct ASC, s.station_id ASC;
