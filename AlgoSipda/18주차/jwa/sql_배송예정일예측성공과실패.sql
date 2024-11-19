-- 2017년 1월
-- 구매 일자별
-- 배송 예정 시각 안에 도착한 주문, 지나서 도착한 주문 각각 집계
-- 배송 완료, 예정 시각 데이터 없는 경우 제외
-- 구매 날짜 기준 오름차순

SELECT DATE(order_purchase_timestamp) AS purchase_date,
    SUM(CASE WHEN order_delivered_customer_date <= order_estimated_delivery_date THEN 1 ELSE 0 END) AS success,
    SUM(CASE WHEN order_delivered_customer_date > order_estimated_delivery_date THEN 1 ELSE 0 END) AS fail
FROM olist_orders_dataset
WHERE strftime('%Y-%m', order_purchase_timestamp) = '2017-01'
    AND order_delivered_customer_date IS NOT NULL
    AND order_estimated_delivery_date IS NOT NULL
GROUP BY DATE(order_purchase_timestamp)
ORDER BY purchase_date;
