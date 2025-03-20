select strftime('%Y-%m-%d', order_purchase_timestamp) as dt, round(sum(payment_value), 2) revenue_daily
from
  (select *
    from olist_orders_dataset
    where strftime('%Y-%m-%d', order_purchase_timestamp) >= '2018-01-01'
  ) orders left join olist_order_payments_dataset payment on orders.order_id = payment.order_id
group by 1
order by 1;