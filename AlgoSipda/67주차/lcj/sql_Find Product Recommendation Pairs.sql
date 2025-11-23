select
    pp1.product_id product1_id,
    pp2.product_id product2_id,
    pi1.category product1_category,
    pi2.category product2_category,
    count(pp1.user_id) customer_count
from
    productpurchases pp1
inner join
    productpurchases pp2
using
    (user_id)
left join
    productinfo pi1
on
    pp1.product_id = pi1.product_id
left join
    productinfo pi2
on
    pp2.product_id = pi2.product_id
where
    pp1.product_id < pp2.product_id
group by
    product1_id, product2_id, product1_category,product2_category
having
    count(pp1.user_id) >= 3
order by
    customer_count desc, product1_id, product2_id;
