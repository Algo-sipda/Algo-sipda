SELECT 
    A.measured_at AS today,
    B.measured_at AS next_day,
    A.pm10 AS pm10,
    B.pm10 AS next_pm10
FROM 
    measurements A
JOIN 
    measurements B
ON 
    DATE(A.measured_at, '+1 day') = B.measured_at
WHERE 
    B.pm10 > A.pm10;
