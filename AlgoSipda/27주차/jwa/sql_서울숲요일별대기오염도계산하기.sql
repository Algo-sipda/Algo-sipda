SELECT 
    CASE strftime('%w', date) 
        WHEN '1' THEN '월요일' 
        WHEN '2' THEN '화요일' 
        WHEN '3' THEN '수요일' 
        WHEN '4' THEN '목요일' 
        WHEN '5' THEN '금요일' 
        WHEN '6' THEN '토요일' 
        WHEN '0' THEN '일요일' 
    END AS weekday,
    ROUND(AVG(no2), 4) AS no2,
    ROUND(AVG(o3), 4) AS o3,
    ROUND(AVG(co), 4) AS co,
    ROUND(AVG(so2), 4) AS so2,
    ROUND(AVG(pm10), 4) AS pm10,
    ROUND(AVG(pm2_5), 4) AS pm2_5
FROM measurements
GROUP BY strftime('%w', date)
ORDER BY strftime('%w', date) = '1', 
         strftime('%w', date) = '2', 
         strftime('%w', date) = '3', 
         strftime('%w', date) = '4', 
         strftime('%w', date) = '5', 
         strftime('%w', date) = '6', 
         strftime('%w', date) = '0';
