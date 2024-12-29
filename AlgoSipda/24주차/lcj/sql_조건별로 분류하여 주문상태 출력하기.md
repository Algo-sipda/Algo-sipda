## Programmers 조건별로 분류하여 주문상태 출력하기

### 🛠️ 문제 🛠️

```
FOOD_ORDER 테이블에서 2022년 5월 1일을 기준으로 주문 ID, 제품 ID, 출고일자, 출고여부를 조회하는 SQL문을 작성해주세요. 출고여부는 2022년 5월 1일까지 출고완료로 이 후 날짜는 출고 대기로 미정이면 출고미정으로 출력해주시고, 결과는 주문 ID를 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131113)

<br/>

### 💡 코드 💡

```sql
SELECT 
        ORDER_ID, 
        PRODUCT_ID, 
        DATE_FORMAT(OUT_DATE, '%Y-%m-%d') AS OUT_DATE, 
        CASE 
            WHEN OUT_DATE <= '2022-05-01' THEN '출고완료'
            WHEN OUT_DATE > '2022-05-01' THEN '출고대기'
            ELSE '출고미정'
        END AS '출고여부'
FROM 
    FOOD_ORDER
ORDER BY 
    ORDER_ID;
```

<br/>

### 📙 개념 📙

[없음]
