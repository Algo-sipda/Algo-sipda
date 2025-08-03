-- https://leetcode.com/problems/list-the-products-ordered-in-a-period/description/
-- 개선된 쿼리 
SELECT 
    p.product_name,
    SUM(o.unit) AS unit
FROM Orders o
JOIN Products p ON p.product_id = o.product_id
WHERE o.order_date >= '2020-02-01'
  AND o.order_date < '2020-03-01'
GROUP BY p.product_name
HAVING SUM(o.unit) >= 100;

-- 상관 서브 쿼리 
-- select (select p.product_name from products p where p.product_id = o.product_id) as product_name, sum(o.unit) as unit 
-- from Orders o 
-- where o.order_date >= '2020-02-01' and o.order_date < '2020-03-01' 
-- group by o.product_id
-- having sum(o.unit) >= 100
-- ;