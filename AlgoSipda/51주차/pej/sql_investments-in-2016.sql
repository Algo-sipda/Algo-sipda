-- [SQL] Investments in 2016
-- https://leetcode.com/problems/investments-in-2016/description/

SELECT ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM Insurance i
WHERE i.tiv_2015 IN (
    SELECT tiv_2015
    FROM Insurance
    GROUP BY tiv_2015
    HAVING COUNT(DISTINCT pid) > 1
)
AND (i.lat, i.lon) IN (
    SELECT lat, lon
    FROM Insurance
    GROUP BY lat, lon
    HAVING COUNT(DISTINCT pid) = 1
);