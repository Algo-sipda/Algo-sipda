-- 🔍SQL
-- [SQL] Find Products with Valid Serial Numbers
-- https://leetcode.com/problems/find-products-with-valid-serial-numbers/description/

SELECT *
FROM products
WHERE REGEXP_LIKE(
    description,
    '\\bSN[0-9]{4}-[0-9]{4}\\b',
    'c'
)
ORDER BY product_id;