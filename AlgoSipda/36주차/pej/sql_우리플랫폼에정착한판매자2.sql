select seller_id, count(distinct order_id) as orders 
from olist_order_items_dataset
where price >= 50 -- 상품가격이 50달러 이상
group by seller_id
having orders >= 100 -- 주문이 100건 이상
order by orders desc; -- 건수가 많은 순서대로 출력
