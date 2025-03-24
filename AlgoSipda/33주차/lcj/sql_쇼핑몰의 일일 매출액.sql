select
  STRFTIME ("%Y-%m-%d", order_purchase_timestamp) as dt,
  ROUND(SUM(payment_value), 2) revenue_daily
from
  olist_orders_dataset d
  join olist_order_payments_dataset p on d.order_id = p.order_id
WHERE
  order_purchase_timestamp > "2018-01-01"
GROUP BY
  dt
ORDER BY
  dt;