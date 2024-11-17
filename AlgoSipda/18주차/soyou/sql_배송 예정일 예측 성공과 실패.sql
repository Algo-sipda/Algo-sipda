SELECT DATE(order_purchase_timestamp) as purchase_date,
    COUNT(CASE WHEN DATE(order_delivered_customer_date) < DATE(order_estimated_delivery_date) THEN order_id END) as success,
    COUNT(CASE WHEN DATE(order_delivered_customer_date) >= DATE(order_estimated_delivery_date) THEN order_id END) as fail
FROM olist_orders_dataset
WHERE order_delivered_customer_date IS NOT NULL
  AND order_estimated_delivery_date IS NOT NULL
  AND DATE(order_purchase_timestamp) BETWEEN '2017-01-01' AND '2017-01-31'
GROUP BY purchase_date
ORDER BY purchase_date;