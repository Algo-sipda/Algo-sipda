SELECT
    DATE(order_purchase_timestamp) AS purchase_date,
    COUNT(CASE WHEN DATE(order_estimated_delivery_date) > DATE(order_delivered_customer_date) THEN ORDER_ID END) success,
    COUNT(CASE WHEN DATE(order_estimated_delivery_date) <= DATE(order_delivered_customer_date) THEN ORDER_ID END) fail
FROM
    olist_orders_dataset
WHERE
    order_delivered_customer_date IS NOT NULL
  AND order_estimated_delivery_date IS NOT NULL
  AND purchase_date BETWEEN '2017-01-01' AND '2017-01-31'
GROUP BY
    purchase_date
ORDER BY
    purchase_date;