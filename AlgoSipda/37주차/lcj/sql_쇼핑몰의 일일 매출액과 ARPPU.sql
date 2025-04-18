-- 두 테이블을 이용해서 2018년 1월 1일 이후 일별로 집계된
-- 쇼핑몰의 결제 고객 수, 매출액, ARPPU를 계산하는 쿼리
-- 매출 날짜 기준으로 오름차순
-- 매출액과 ARPPU는 반올림해 소수점 둘째자리까지 출력
-- ARPPU: 결제 고객 1인당 평균 결제 금액
select
  date(order_purchase_timestamp) dt,
  count(distinct customer_id) pu,
  round(sum(payment_value), 2) revenue_daily,
  round(
    sum(payment_value) / count(distinct customer_id),
    2
  ) arppu
from
  olist_orders_dataset d
  join olist_order_payments_dataset pd on d.order_id = pd.order_id
where
  order_purchase_timestamp >= '2018-01-01'
group by
  date(order_purchase_timestamp)
order by
  date(order_purchase_timestamp);