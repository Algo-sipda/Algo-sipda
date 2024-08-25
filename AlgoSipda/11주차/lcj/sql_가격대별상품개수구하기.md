## Programmers 가격대 별 상품 개수 구하기

### 🛠️ 문제 🛠️

```
PRODUCT 테이블에서 만원 단위의 가격대 별로 상품 개수를 출력하는 SQL 문을 작성해주세요. 이때 컬럼명은 각각 컬럼명은 PRICE_GROUP, PRODUCTS로 지정해주시고 가격대 정보는 각 구간의 최소금액(10,000원 이상 ~ 20,000 미만인 구간인 경우 10,000)으로 표시해주세요. 결과는 가격대를 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131530)

<br/>

### 💡 코드 💡

```sql
SELECT (CASE 
            WHEN PRICE < 10000 THEN 0
            ELSE TRUNCATE(PRICE, -4)
        END) AS PRICE_GROUP, COUNT(PRODUCT_ID) AS PRODUCTS 
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP;
```

<br/>

### 📙 Mysql 개념 📙
[없음]
