SELECT
  DATE(o.order_purchase_timestamp) AS dt,
  COUNT(DISTINCT o.customer_id) AS pu,
  ROUND(SUM(p.payment_value), 2) AS revenue_daily,
  ROUND(SUM(p.payment_value) * 1.0 / COUNT(DISTINCT o.customer_id), 2) AS arppu
FROM olist_orders_dataset o
JOIN olist_order_payments_dataset p
  ON o.order_id = p.order_id
WHERE DATE(o.order_purchase_timestamp) >= '2018-01-01'
GROUP BY dt
ORDER BY dt;
