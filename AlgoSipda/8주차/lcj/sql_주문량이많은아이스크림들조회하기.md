## Programmers 주문량이 많은 아이스크림들 조회하기

### 🛠️ 문제 🛠️

```
7월 아이스크림 총 주문량과 상반기의 아이스크림 총 주문량을 더한 값이 큰 순서대로 상위 3개의 맛을 조회하는 SQL 문을 작성해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/133027)

<br/>

### 💡 코드 💡

```sql
-- 7월의 아이스크림 총 주문량
-- 주문량 순서대로 상위 3개의 맛만 조회
SELECT FH.FLAVOR
FROM FIRST_HALF FH
JOIN JULY J
ON FH.FLAVOR = J.FLAVOR
GROUP BY FH.FLAVOR
ORDER BY (SUM(FH.TOTAL_ORDER) + SUM(J.TOTAL_ORDER)) DESC
LIMIT 3;
```

<br/>

### 📙 Mysql 개념 📙

[없음]
