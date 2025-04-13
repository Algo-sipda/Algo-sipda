-- 상품 가격이 50달러 이상인 주문이 100건 이상 들어온 판매자 리스트를 주문 건수가 많은 순서대로 출력
select seller_id, count(distinct order_id) as orders
from olist_order_items_dataset
where price >= 50
group by seller_id
HAVING count(distinct order_id) >= 100
order by orders desc;