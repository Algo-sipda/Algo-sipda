SELECT YEAR(ym) as YEAR, ROUND(AVG(PM_VAL1), 2) AS PM10, ROUND(AVG(PM_VAL2), 2) as "PM2.5"
FROM air_pollution
WHERE location2 = "수원"
GROUP BY year
ORDER BY year