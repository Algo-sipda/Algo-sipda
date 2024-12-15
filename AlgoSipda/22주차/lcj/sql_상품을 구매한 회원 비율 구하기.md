## Programmers 상품을 구매한 회원 비율 구하기

### 🛠️ 문제 🛠️

```
USER_INFO 테이블과 ONLINE_SALE 테이블에서 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수와 상품을 구매한 회원의 비율(=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)을 년, 월 별로 출력하는 SQL문을 작성해주세요. 상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림하고, 전체 결과는 년을 기준으로 오름차순 정렬해주시고 년이 같다면 월을 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/131534)

<br/>

### 💡 코드 💡

```sql
-- 2021년에 가입한 전체 회원 중
-- 상품을 구매한 회원수와 상품을 구매한 회원의 비율(상품을 구매한 회원 수 / 전체 회원 수)
-- 상품을 구매한 회원의 비율은 소수점 두번째자리에서 반올림
-- 년을 기준 오름차순, 월 기준 오름차순
SELECT YEAR(S.SALES_DATE) AS YEAR, 
        MONTH(S.SALES_DATE) AS MONTH,
        COUNT(DISTINCT I.USER_ID) AS PURCHASED_USERS,
        ROUND(COUNT(DISTINCT I.USER_ID) / (SELECT COUNT(DISTINCT USER_ID) 
                                    FROM USER_INFO 
                                    WHERE YEAR(JOINED) = '2021'), 1) AS PUCHASED_RATIO
FROM USER_INFO I
JOIN ONLINE_SALE S
ON I.USER_ID = S.USER_ID
    AND YEAR(I.JOINED) = '2021'
GROUP BY YEAR(S.SALES_DATE), MONTH(S.SALES_DATE)
ORDER BY YEAR, MONTH;
```

<br/>

### 📙 개념 📙

[없음]
