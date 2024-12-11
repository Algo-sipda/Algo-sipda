## Programmers 성분으로 구분한 아이스크림 총 주문량

### 🛠️ 문제 🛠️

```
상반기 동안 각 아이스크림 성분 타입과 성분 타입에 대한 아이스크림의 총주문량을 총주문량이 작은 순서대로 조회하는 SQL 문을 작성해주세요. 이때 총주문량을 나타내는 컬럼명은 TOTAL_ORDER로 지정해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/133026)

<br/>

### 💡 코드 💡

```sql
SELECT II.INGREDIENT_TYPE, SUM(FH.TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF FH
JOIN ICECREAM_INFO II
ON FH.FLAVOR = II.FLAVOR
GROUP BY II.INGREDIENT_TYPE
ORDER BY FH.FLAVOR;

```

<br/>

### 📙 개념 📙

[없음]
