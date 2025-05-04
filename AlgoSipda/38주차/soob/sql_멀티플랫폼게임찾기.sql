SELECT
    name
FROM (
    SELECT DISTINCT
        g.name,
        CASE
            WHEN p.name IN ('PS3', 'PS4', 'PSP', 'PSV') THEN 1
            WHEN p.name IN ('Wii', 'WiiU', 'DS', '3DS') THEN 2
            WHEN p.name IN ('X360', 'XONE') THEN 3
        END AS major
    FROM
        games g
    LEFT JOIN
        platforms p ON g.platform_id = p.platform_id
    WHERE
        g.year >= 2012
)
WHERE
    major IS NOT NULL
GROUP BY
    name
HAVING
    COUNT(*) >= 2;