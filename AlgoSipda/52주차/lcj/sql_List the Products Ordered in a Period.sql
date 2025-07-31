# 2020년 2월 최소 100개
select p.product_name, sum(unit) as unit
from products p
join orders o
on p.product_id = o.product_id
where o.order_date like '2020-02-%'
group by p.product_id
having sum(unit) >= 100