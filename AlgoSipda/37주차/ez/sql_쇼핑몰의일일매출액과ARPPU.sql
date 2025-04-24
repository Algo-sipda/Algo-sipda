select
  date(order_purchase_timestamp) dt,
  count(distinct customer_id) pu,
  round(sum(payment_value), 2) revenue_daily,
  round(sum(payment_value) / count(distinct customer_id), 2) arppu
from olist_order_payments_dataset p
left join olist_orders_dataset o
on p.order_id = o.order_id
where date(order_purchase_timestamp) >= '2018-01-01'
group by 1
order by 1;