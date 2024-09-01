## Pro 특정 기간동안 대여 가능한 자동차들의 대여비용 구하기

---

### 문제
```
할인율이 적용되는 대여 기간 종류로는 '7일 이상' (대여 기간이 7일 이상 30일 미만인 경우), '30일 이상' (대여 기간이 30일 이상 90일 미만인 경우), '90일 이상' (대여 기간이 90일 이상인 경우) 이 있습니다. 

대여 기간이 7일 미만인 경우 할인정책이 없습니다.
```

### 코드
```sql
WITH CTE AS(SELECT C.CAR_ID,C.CAR_TYPE,TRUNCATE(C.DAILY_FEE * 30 *((100-H.DISCOUNT_RATE)/100),0) AS FEE
            FROM CAR_RENTAL_COMPANY_CAR AS C,(SELECT CAR_TYPE ,DISCOUNT_RATE
                                              FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                                              WHERE DURATION_TYPE = '30일 이상'
            ) AS H
            WHERE C.CAR_ID NOT IN(SELECT DISTINCT CAR_ID
                                  FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                                  WHERE END_DATE LIKE '2022-11%' OR START_DATE LIKE '2022-11%'
                                     OR(START_DATE < '2022-11-01' AND END_DATE > '2022-11-30'))
              AND C.CAR_TYPE IN ('세단','SUV')
              AND C.CAR_TYPE =H.CAR_TYPE
            ORDER BY 3 DESC,2 ,1 DESC)

SELECT *
FROM CTE
WHERE FEE BETWEEN 500000 AND 2000000
ORDER BY FEE DESC , CAR_TYPE ,CAR_ID DESC
```