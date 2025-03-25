-- 2018년 1월 1일 이후 일일 매출액
-- 주문 날짜 필터링
-- 조인, 주문 아이디
-- 일별로 그룹, 매출액 합

WITH orders AS (
  SELECT order_id, order_purchase_timestamp
  FROM olist_orders_dataset
  WHERE order_purchase_timestamp >= '2018-01-01'
)

SELECT DATE(o.order_purchase_timestamp) AS dt, ROUND(SUM(p.payment_value), 2) AS revenue_daily
FROM orders o
LEFT JOIN olist_order_payments_dataset p USING(order_id)
GROUP BY DATE(o.order_purchase_timestamp)
ORDER BY dt;
