-- SUBSTR: 문자열 잘라 추출
-- INSTR: 특정 문자 위치값 반환

select sido, substr(sigungu_detail, 1, instr(sigungu_detail, ' ') - 1) sigungu, count(cafe_id) cnt
from (
  select cafe_id, substr(address, 1, instr(address, ' ') - 1) sido,
    substr(address, instr(address, ' ') + 1, length(address)) sigungu_detail
  from cafes
)
group by sido, sigungu
order by cnt desc;