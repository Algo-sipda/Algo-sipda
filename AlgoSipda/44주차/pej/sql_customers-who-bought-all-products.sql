-- [SQL] Customers Who Bought All Products https://leetcode.com/problems/customers-who-bought-all-products/description/

SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(product_key) FROM Product);