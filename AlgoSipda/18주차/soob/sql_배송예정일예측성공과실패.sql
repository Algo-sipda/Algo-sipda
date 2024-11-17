WITH CHECK_TABLE AS (
  SELECT DATE(order_purchase_timestamp) AS purchase_date,
    CASE
      WHEN order_estimated_delivery_date >= order_delivered_customer_date THEN 1
      ELSE 0
    END AS success
  FROM olist_orders_dataset
  WHERE strftime('%Y', order_purchase_timestamp) = '2017'
    AND strftime('%m', order_purchase_timestamp) = '01'
    AND order_delivered_customer_date IS NOT NULL
    AND order_estimated_delivery_date IS NOT NULL
)
SELECT 
  purchase_date, 
  SUM(CASE WHEN success = 1 THEN 1 ELSE 0 END) AS success, 
  SUM(CASE WHEN success = 0 THEN 1 ELSE 0 END) AS fail
FROM CHECK_TABLE
GROUP BY purchase_date
ORDER BY purchase_date;
