-- [SQL] 멀티 플랫폼 게임 찾기 https://solvesql.com/problems/multiplatform-games/

SELECT name
FROM (
    SELECT G.name,
           COUNT(
                DISTINCT CASE
                    WHEN P.name IN ('PS3', 'PS4', 'PSP', 'PSV') THEN 'Sony'
                    WHEN P.name IN ('Wii', 'WiiU', 'DS', '3DS') THEN 'Nintendo'
                    WHEN P.name IN ('X360', 'XONE') THEN 'Microsoft'
                END
            ) AS major_family_count
    FROM GAMES AS G
    JOIN PLATFORMS AS P ON G.platform_id = P.platform_id
    WHERE G.year >= 2012
    AND P.name IN (
          'PS3', 'PS4', 'PSP', 'PSV',
          'Wii', 'WiiU', 'DS', '3DS',
          'X360', 'XONE'
    )
    GROUP BY G.name
) AS sub
WHERE major_family_count >= 2;
