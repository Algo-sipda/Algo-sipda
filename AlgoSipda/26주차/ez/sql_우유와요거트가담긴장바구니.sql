SELECT CART_ID FROM CART_PRODUCTS
WHERE NAME IN ('MILK', 'YOGURT')
GROUP BY CART_ID
HAVING COUNT(DISTINCT NAME) >= 2
ORDER BY ID;