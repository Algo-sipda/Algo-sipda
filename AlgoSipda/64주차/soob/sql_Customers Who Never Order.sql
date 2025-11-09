SELECT name AS Customers
FROM customers c
LEFT JOIN
(SELECT customerid, COUNT(*) as ordercount
FROM orders
GROUP BY customerid
) o
ON c.id = o.customerid
WHERE ordercount IS NULL;