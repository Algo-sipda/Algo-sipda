SELECT
    p1.product_id product1_id, p2.product_id product2_id, pi1.category product1_category, pi2.category product2_category, COUNT(p1.user_id) customer_count
FROM productpurchases p1 JOIN productpurchases p2 ON p1.user_id = p2.user_id AND p1.product_id < p2.product_id
    JOIN ProductInfo pi1 ON pi1.product_id = p1.product_id
    JOIN ProductInfo pi2 ON pi2.product_id = p2.product_id
GROUP BY product1_id, product2_id
HAVING customer_count >= 3
ORDER BY customer_count DESC, product1_id, product2_id;