-- 매출 날짜, 결제 고객 수, 해당 날짜의 매출액, 결제 고객 1인당 평균 결제 금액 
-- FROM / JOIN > WHERE > GROUP BY > HAVING > SELECT > ORDER BY 순으로 실행됨 
-- 따라서, alias는 아직 존재하지 않음

SELECT 
DATE(order_purchase_timestamp) as dt ,
COUNT(DISTINCT customer_id) as pu, 
ROUND(SUM(payment_value),2) as revenue_daily ,
ROUND(SUM(payment_value) / COUNT(DISTINCT customer_id),2) as arppu
FROM (
  SELECT * 
  FROM olist_orders_dataset as ood 
  LEFT JOIN olist_order_payments_dataset as oopd
  ON ood.order_id = oopd.order_id
)
WHERE order_purchase_timestamp >= '2018-01-01 00:00:00'
GROUP BY DATE(order_purchase_timestamp)
ORDER BY DATE(order_purchase_timestamp);
