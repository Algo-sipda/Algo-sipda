WITH
  platform_group AS (
    SELECT
      g.name,
      CASE
        WHEN p.name IN ('PS3', 'PS4', 'PSP', 'PSV') THEN 'Sony'
        WHEN p.name IN ('Wii', 'WiiU', 'DS', '3DS') THEN 'Nintendo'
        WHEN p.name IN ('X360', 'XONE') THEN 'Microsoft'
        ELSE NULL
      END AS major_group
    FROM
      games g
      JOIN platforms p ON g.platform_id = p.platform_id
    WHERE
      g.year >= 2012
  )
SELECT DISTINCT
  name
FROM
  platform_group
WHERE
  major_group IS NOT NULL
GROUP BY
  name
HAVING
  COUNT(DISTINCT major_group) >= 2;