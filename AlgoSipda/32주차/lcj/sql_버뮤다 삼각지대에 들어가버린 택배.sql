select
  strftime ("%Y-%m-%d", order_delivered_carrier_date) as delivered_carrier_date,
  count(order_id) as orders
from
  olist_orders_dataset
where
  1 = 1
  and order_delivered_carrier_date like "2017-01%"
  and order_delivered_customer_date is null
group by
  1
order by
  1;