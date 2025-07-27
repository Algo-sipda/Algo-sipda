# 2016년 모든 보험계약자의 총 투자 가치 합계
# 소수점 둘째자리 반올림
# 2015 값은 1명 이상의 다른 보험 계약자와 중복되어야 함
# 중복 없는 위도 경도 정보 조회
select
    round(sum(tiv_2016), 2) as tiv_2016
from
    insurance
where
    1 = 1
    and tiv_2015 in (select tiv_2015
                    from insurance
                    group by tiv_2015
                    having count(tiv_2015) >= 2)
    and pid in (select pid
                from insurance
                group by lat, lon
                having count(lat) = 1);