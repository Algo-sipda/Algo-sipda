SELECT
  c.classification,
  SUM(CASE WHEN strftime('%Y', a.acquisition_date) = '2014' THEN 1 ELSE 0 END) AS "2014",
  SUM(CASE WHEN strftime('%Y', a.acquisition_date) = '2015' THEN 1 ELSE 0 END) AS "2015",
  SUM(CASE WHEN strftime('%Y', a.acquisition_date) = '2016' THEN 1 ELSE 0 END) AS "2016"
FROM (
  SELECT DISTINCT classification
  FROM artworks
) c
LEFT JOIN artworks a
  ON a.classification = c.classification
  AND strftime('%Y', a.acquisition_date) IN ('2014', '2015', '2016')
GROUP BY c.classification
ORDER BY c.classification;
