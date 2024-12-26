-- 2022년 5월 1일 기준 출고여부
-- 출고완료/출고대기/출고미정
-- 주문 id 오름차순

SELECT
    ORDER_ID,
    PRODUCT_ID,
    DATE_FORMAT(out_date, '%Y-%m-%d') AS OUT_DATE,
    CASE
        WHEN out_date IS NULL THEN '출고미정'
        WHEN out_date <= '2022-05-01' THEN '출고완료'
        WHEN out_date > '2022-05-01' THEN '출고대기'
    END AS '출고여부'
FROM FOOD_ORDER
ORDER BY order_id;