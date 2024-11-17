## Programmers 배송 예정일 예측 성공과 실패

### 🛠️ 문제 🛠️

```
2017년 1월 한 달 동안 발생한 주문의 배송 예측이 정확했는지 분석을 하려고 합니다. 고객의 구매 일자별로 배송 예정 시각 안에 고객에게 도착한 주문과, 배송 예정 시각이 지나서 고객에게 도착한 주문을 각각 집계하는 쿼리를 작성해주세요. 배송 완료 또는 배송 예정 시각 데이터가 없는 경우는 계산에서 제외합니다. 계산 결과는 구매 날짜를 기준으로 오름차순 정렬되어야 하고, 아래 컬럼을 포함해야 합니다.
```

[문제 바로가기](https://solvesql.com/problems/estimated-delivery-date/)

<br/>

### 💡 코드 💡

```sql
-- 2017년 1월 한 달 동안 발생한 주문의 배송 예측 분석
-- 배송 예정 시간 안에 고객에게 도착한 주문과, 배송 예정 시각이 지나서 고객에게 도착한 주문을 각각 집계하는 쿼리
-- 배송 완료 또는 배송 예정 시각 데이터가 없는 경우는 계산에서 제외
-- 계산 결과는 구매 날짜를 기준으로 오름차순 정렬되어야 하고, 아래 컬럼을 포함
SELECT
  strftime ("%Y-%m-%d", order_purchase_timestamp) AS "purchase_date",
  COUNT(
    DISTINCT CASE
      WHEN order_estimated_delivery_date >= order_delivered_customer_date THEN order_id
    END
  ) "success",
  COUNT(
    DISTINCT CASE
      WHEN order_estimated_delivery_date < order_delivered_customer_date THEN order_id
    END
  ) "fail"
FROM
  olist_orders_dataset
WHERE
  1 = 1
  AND strftime ("%Y-%m", order_purchase_timestamp) = "2017-01"
  AND order_delivered_carrier_date IS NOT NULL
  AND order_delivered_customer_date IS NOT NULL
GROUP BY
  purchase_date
ORDER BY
  purchase_date;

```

<br/>

### 📙 개념 📙

1. SQLLite에서는 date_format 대신 strftime을 사용한다.

- 형식, 입력 칼럼 순으로 되어있다.

2. SQLLite에서는 if문 대신 case then을 사용한다.
