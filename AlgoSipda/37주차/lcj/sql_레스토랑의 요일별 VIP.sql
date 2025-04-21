-- 요일별로 가장 높은 금액의 결제 내역 출력
select
  *
from
  tips
where
  total_bill in (
    select
      max(total_bill)
    from
      tips
    group by
      day
  );