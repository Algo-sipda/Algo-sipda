SELECT 
    DATE(o.order_purchase_timestamp) AS dt,
    SUM(p.payment_value) AS revenue_daily
FROM olist_orders_dataset o
JOIN olist_order_payments_dataset p
  ON o.order_id = p.order_id
WHERE dt >= '2018-01-01'
GROUP BY dt
ORDER BY dt;
