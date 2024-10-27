## Programmers 식품분류별 가장 비싼 식품의 정보 조회하기

### 🛠️ 문제 🛠️

```
FOOD_PRODUCT 테이블에서 식품분류별로 가격이 제일 비싼 식품의 분류, 가격, 이름을 조회하는 SQL문을 작성해주세요. 이때 식품분류가 '과자', '국', '김치', '식용유'인 경우만 출력시켜 주시고 결과는 식품 가격을 기준으로 내림차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131116)

<br/>

### 💡 코드 💡

```sql
SELECT CATEGORY, PRICE AS MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT
WHERE 1 = 1
    AND PRICE IN(SELECT MAX(PRICE)
              FROM FOOD_PRODUCT
              GROUP BY CATEGORY)
    AND CATEGORY IN ('과자', '국', '김치', '식용유')
ORDER BY MAX_PRICE DESC

```

<br/>

### 📙 개념 📙

[없음]
