select seller_id, count(distinct order_id) orders
from olist_order_items_dataset
where price >= 50
group by seller_id
having count(distinct order_id) >= 100
order by orders desc
;
