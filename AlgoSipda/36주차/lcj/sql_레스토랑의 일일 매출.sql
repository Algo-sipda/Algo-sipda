-- 레스토랑의 요일별 매출 총액을 계산
-- 요일별로 매출 총액을 집계하는 쿼리
-- 1000달러 미만으로 판매한 날은 결과에서 제외
-- 매출이 많았던 요일부터 순서대로 출력

SELECT day, sum(total_bill) as revenue_daily
from tips
group by day
having sum(total_bill) >= 1000
order by revenue_daily desc;