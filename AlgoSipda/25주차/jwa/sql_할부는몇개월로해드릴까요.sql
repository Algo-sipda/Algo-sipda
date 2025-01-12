SELECT payment_installments, COUNT(distinct order_id) AS order_count,
      MIN(payment_value) as min_value,
      MAX(payment_value) as max_value,
      AVG(payment_value) as avg_value
FROM olist_order_payments_dataset
WHERE payment_type = 'credit_card'
GROUP BY payment_installments;
