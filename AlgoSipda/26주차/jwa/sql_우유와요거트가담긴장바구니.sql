-- 우유, 요거트 필터링
-- 카트 아이디 그룹화
-- 이름 중복 제외한 개수가 2 이상

SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME IN ('Milk', 'Yogurt')
GROUP BY CART_ID
HAVING COUNT(DISTINCT NAME) = 2
ORDER BY CART_ID;