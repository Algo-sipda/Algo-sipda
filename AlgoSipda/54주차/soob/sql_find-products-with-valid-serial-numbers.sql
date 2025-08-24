SELECT product_id, product_name, description
FROM Products
WHERE REGEXP_LIKE(description, '(^|[^A-Za-z0-9])SN[0-9]{4}-[0-9]{4}([^0-9]|$)', 'c')
ORDER BY product_id;
