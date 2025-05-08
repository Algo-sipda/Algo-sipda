-- [SQL] 폐쇄할 따릉이 정류소 찾기 2 https://solvesql.com/problems/find-unnecessary-station-2/
-- 1. 2018.10 대여/반납 건수 : 1.1과 1.2 구할 때 각기 다른 필드에 위치하고 있음 -> UNION ALL로 합치기  
-- 1.1. 2018.10 대여 건수 
-- 1.2. 2018.10 반납 건수   
-- 2. 2019.10 대여/반납 건수
SELECT s.station_id, s.name, s.local,ROUND(u19.station_cnt * 100.0 / u18.station_cnt, 2)  as usage_pct
FROM station s
JOIN (
  SELECT combined_2018.id as station_id, count(combined_2018.id) as station_cnt
  FROM (
    SELECT rent_station_id as id
    FROM rental_history
    WHERE rent_at BETWEEN '2018-10-01' AND '2018-10-31 23:59:59'
    UNION ALL
    SELECT return_station_id as id
    FROM rental_history
    WHERE return_at BETWEEN '2018-10-01' AND '2018-10-31 23:59:59'
  ) AS combined_2018
  GROUP BY combined_2018.id
  HAVING count(combined_2018.id) > 0
) as u18 ON s.station_id = u18.station_id
JOIN (
  SELECT combined_2019.id as station_id, count(combined_2019.id) as station_cnt
  FROM (
    SELECT rent_station_id as id
    FROM rental_history
    WHERE rent_at BETWEEN '2019-10-01' AND '2019-10-31 23:59:59'
    UNION ALL
    SELECT return_station_id as id
    FROM rental_history
    WHERE return_at BETWEEN '2019-10-01' AND '2019-10-31 23:59:59'
  ) AS combined_2019
  GROUP BY combined_2019.id
  HAVING count(combined_2019.id) > 0
) as u19 ON s.station_id = u19.station_id
WHERE ROUND(u19.station_cnt * 100.0 / u18.station_cnt, 2) <= 50.0;


