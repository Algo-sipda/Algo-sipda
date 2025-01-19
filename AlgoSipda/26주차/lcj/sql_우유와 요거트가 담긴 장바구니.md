## Programmers 우유와 요거트가 담긴 장바구니


### 🛠️ 문제 🛠️

```
데이터 분석 팀에서는 우유(Milk)와 요거트(Yogurt)를 동시에 구입한 장바구니가 있는지 알아보려 합니다. 우유와 요거트를 동시에 구입한 장바구니의 아이디를 조회하는 SQL 문을 작성해주세요. 이때 결과는 장바구니의 아이디 순으로 나와야 합니다.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/62284)

<br/>

### 💡 코드 💡

```sql
-- 우유(Milk)와 요거트(Yogurt)를 동시에 구입한 장바구니 구하기
-- 구입한 장바구니의 아이디 조회
-- 아이디 순으로 
WITH M AS (
    SELECT CART_ID, NAME
    FROM CART_PRODUCTS
    WHERE NAME = 'Milk'
),
    Y AS (
    SELECT CART_ID, NAME
    FROM CART_PRODUCTS
    WHERE NAME = 'Yogurt'
)

SELECT M.CART_ID
FROM M
JOIN Y
ON M.CART_ID = Y.CART_ID
ORDER BY M.CART_ID;
```

```sql
-- 풀이 2번
SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME IN ('Milk','Yogurt')
GROUP BY CART_ID
HAVING COUNT(DISTINCT NAME) = 2;
```

<br/>

### 📙 개념 📙

[없음]
