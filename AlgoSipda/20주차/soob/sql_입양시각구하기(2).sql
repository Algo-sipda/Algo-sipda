WITH RECURSIVE HOUR_TABLE AS (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1 AS HOUR
    FROM HOUR_TABLE
    WHERE HOUR < 23
)
SELECT *
FROM HOUR_TABLE