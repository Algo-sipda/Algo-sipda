SELECT 
    m1.measured_at AS today, 
    DATE(m1.measured_at, '+1 day') AS next_day,
    m1.pm10 AS pm10, 
    m2.pm10 AS next_pm10
FROM 
    measurements m1
LEFT JOIN 
    measurements m2
ON 
    m2.measured_at = DATE(m1.measured_at, '+1 day')
WHERE m1.pm10 < m2.pm10;
