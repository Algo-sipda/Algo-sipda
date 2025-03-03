-- 2024년 6월 기준 서울에서 제주까지 전국 각지에 잇는 카페 기본 정보
-- 행정구역 별로 몇 개의 카페가 있는지 집계하는 쿼리
-- 카페가 가장 많은 행정구역 순으로 출력
with
  cfs as (
    select
      cafe_id,
      substr(address, 1, instr(address, ' ') -1) sido,
      substr(
        address,
        instr(address, ' ') + 1,
        instr(substr(address, instr(address, ' ') + 1), ' ') -1
      ) sigungu
    from
      cafes
  )
select
  sido,
  sigungu,
  count(distinct cafe_id) cnt
from
  cfs
group by
  sido,
  sigungu
order by
  cnt desc;