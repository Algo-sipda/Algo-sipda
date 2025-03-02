WITH seasonal_data AS (
    SELECT
        CASE 
            WHEN strftime('%m', measured_at) IN ('03', '04', '05') THEN 'spring'
            WHEN strftime('%m', measured_at) IN ('06', '07', '08') THEN 'summer'
            WHEN strftime('%m', measured_at) IN ('09', '10', '11') THEN 'autumn'
            ELSE 'winter'
        END AS season,
        pm10
    FROM measurements
    WHERE pm10 IS NOT NULL
),

median_data AS (
    SELECT season, pm10,
           ROW_NUMBER() OVER (PARTITION BY season ORDER BY pm10) AS row_num,
           COUNT(*) OVER (PARTITION BY season) AS total_count
    FROM seasonal_data
)

SELECT 
    season,
    ROUND(AVG(pm10), 2) AS pm10_average,
    (SELECT pm10 FROM median_data 
     WHERE median_data.season = s.season 
     AND row_num = (total_count + 1) / 2) AS pm10_median
FROM seasonal_data AS s
GROUP BY season
ORDER BY 
    CASE season 
        WHEN 'spring' THEN 1 
        WHEN 'summer' THEN 2 
        WHEN 'autumn' THEN 3 
        WHEN 'winter' THEN 4 
    END;
