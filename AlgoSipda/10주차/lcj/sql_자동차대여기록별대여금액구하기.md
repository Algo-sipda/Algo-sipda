## Programmers 자동차 대여 기록 별 대여 금액 구하기

### 🛠️ 문제 🛠️

```
CAR_RENTAL_COMPANY_CAR 테이블과 CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블과 CAR_RENTAL_COMPANY_DISCOUNT_PLAN 테이블에서 자동차 종류가 '트럭'인 자동차의 대여 기록에 대해서 대여 기록 별로 대여 금액(컬럼명: FEE)을 구하여 대여 기록 ID와 대여 금액 리스트를 출력하는 SQL문을 작성해주세요. 결과는 대여 금액을 기준으로 내림차순 정렬하고, 대여 금액이 같은 경우 대여 기록 ID를 기준으로 내림차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/151141)

<br/>

### 💡 코드 💡

```sql
WITH A AS (SELECT H.HISTORY_ID, C.DAILY_FEE, C.CAR_TYPE, DATEDIFF(H.END_DATE, H.START_DATE)+1 AS RENTAL_DATE,
                    CASE
                        WHEN DATEDIFF(H.END_DATE, H.START_DATE)+1 >= 90 THEN '90일 이상'
                        WHEN DATEDIFF(H.END_DATE, H.START_DATE)+1 >= 30 THEN '30일 이상'
                        WHEN DATEDIFF(H.END_DATE, H.START_DATE)+1 >= 7 THEN '7일 이상'
                        ELSE '7일 이하'
                    END RENTAL_TYPE
          FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
          JOIN CAR_RENTAL_COMPANY_CAR C
          ON H.CAR_ID = C.CAR_ID
          WHERE C.CAR_TYPE = '트럭')

SELECT A.HISTORY_ID, 
        ROUND(A.DAILY_FEE * A.RENTAL_DATE * (100 - IFNULL(P.DISCOUNT_RATE, 0))* 0.01) AS FEE
FROM A
LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
ON A.RENTAL_TYPE = P.DURATION_TYPE
    AND A.CAR_TYPE = P.CAR_TYPE
ORDER BY FEE DESC, A.HISTORY_ID DESC;
```

<br/>

### 📙 Mysql 개념 📙
[없음]
