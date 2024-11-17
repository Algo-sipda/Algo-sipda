## Programmers 년, 월, 성별 별 상품 구매 회원 수 구하기

### 🛠️ 문제 🛠️

```
USER_INFO 테이블과 ONLINE_SALE 테이블에서 년, 월, 성별 별로 상품을 구매한 회원수를 집계하는 SQL문을 작성해주세요. 결과는 년, 월, 성별을 기준으로 오름차순 정렬해주세요. 이때, 성별 정보가 없는 경우 결과에서 제외해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131532)

<br/>

### 💡 코드 💡

```sql
-- 년, 월, 성별 별로 상품을 구매한 회원수를 집계
-- 년, 월, 성별을 기준 오름차순
-- 성별 정보 x -> 결과 제외
SELECT YEAR(OS.SALES_DATE) AS YEAR, MONTH(OS.SALES_DATE) AS MONTH, UI.GENDER, COUNT(DISTINCT OS.USER_ID) AS USERS
FROM ONLINE_SALE OS
JOIN USER_INFO UI
ON OS.USER_ID = UI.USER_ID
WHERE GENDER IS NOT NULL
GROUP BY YEAR, MONTH, GENDER
ORDER BY YEAR, MONTH, GENDER;


```

<br/>

### 📙 개념 📙

[없음]
