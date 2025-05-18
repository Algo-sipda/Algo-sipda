-- [SQL] 3년간 들어온 소장품 집계하기 https://solvesql.com/problems/summary-of-artworks-in-3-years/
-- 2014 ~ 2016까지 3년간 어떤 분류의 소장품이 많이 추가
SELECT classification, 
COUNT(
  CASE WHEN acquisition_date >= '2014-01-01' AND acquisition_date <= '2014-12-31' THEN 1 END
) as '2014', 
COUNT(
  CASE WHEN acquisition_date >= '2015-01-01' AND acquisition_date <= '2015-12-31' THEN 1 END
) as '2015', 
COUNT(
  CASE WHEN acquisition_date >= '2016-01-01' AND acquisition_date <= '2016-12-31' THEN 1 END
) as '2016'
FROM artworks
GROUP BY classification
ORDER BY classification
;

