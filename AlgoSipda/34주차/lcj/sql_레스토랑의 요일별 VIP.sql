-- 요일별로 가장 높은 금액의 결제 내역을 출력하는 쿼리

select *
from tips
group by day
having total_bill = max(total_bill);