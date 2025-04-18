-- 1달에 주행 거리가 50km 이상인 자전거가 정기점검 대상
-- 2021년 2월 정기점검 대상 자전거 추출
-- 2021년 1월 한 달간 총 주행 거리가 50km 이상인 자전거의 id 출력
select
  bike_id
from
  rental_history
where
  rent_at like '2021-01%'
group by
  bike_id
having
  sum(distance) >= 50000;