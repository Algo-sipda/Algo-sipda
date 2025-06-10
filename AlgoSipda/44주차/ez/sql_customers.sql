select customer_id
from Customer c left join Product p on c.product_key = p.product_key
where p.product_key is not null
group by customer_id
having count(distinct p.product_key) >= (select count(*) from Product);