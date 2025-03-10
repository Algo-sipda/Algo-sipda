select strftime('%Y-%m-%d', order_delivered_carrier_date) delivered_carrier_date, count(*) orders
from olist_orders_dataset
where strftime('%Y-%m', order_delivered_carrier_date) = '2017-01' and order_delivered_customer_date is null
group by 1
order by 1;