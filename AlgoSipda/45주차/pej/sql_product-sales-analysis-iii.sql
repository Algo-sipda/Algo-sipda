-- [SQL] product-sales-analysis-iii https://leetcode.com/problems/product-sales-analysis-iii

select product_id, year as first_year, quantity, price
from Sales
where (product_id, year) IN ( 
select product_id, min(year) as first_year
from Sales
group by product_id
);