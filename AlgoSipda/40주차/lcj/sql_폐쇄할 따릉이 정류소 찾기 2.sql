WITH
  historyOct2018and2019 AS (
    SELECT
      rent_station_id,
      return_station_id,
      strftime ('%Y', rent_at) AS year
    FROM
      rental_history
    WHERE
      (
        rent_at >= '2018-10-01'
        AND rent_at < '2018-11-01'
      )
      OR (
        rent_at >= '2019-10-01'
        AND rent_at < '2019-11-01'
      )
  ),
  stationUsage AS (
    SELECT
      station_id,
      year,
      COUNT(*) AS usageCnt
    FROM
      (
        SELECT
          rent_station_id AS station_id,
          year
        FROM
          historyOct2018and2019
        UNION ALL
        SELECT
          return_station_id AS station_id,
          year
        FROM
          historyOct2018and2019
      ) AS allUsage
    GROUP BY
      station_id,
      year
    HAVING
      usageCnt > 0
  ),
  usageComparison AS (
    SELECT
      su1.station_id,
      ROUND(
        CAST(su2.usageCnt AS FLOAT) / su1.usageCnt * 100,
        2
      ) AS usage_pct
    FROM
      stationUsage su1
      JOIN stationUsage su2 ON su1.station_id = su2.station_id
    WHERE
      su1.year = '2018'
      AND su2.year = '2019'
  )
SELECT
  uc.station_id,
  s.name,
  s.local,
  uc.usage_pct
FROM
  usageComparison uc
  JOIN station s ON uc.station_id = s.station_id
WHERE
  uc.usage_pct <= 50
ORDER BY
  uc.station_id;