WITH CANNOT_RENTAL AS (
    SELECT DISTINCT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE <= '2022-11-30' AND END_DATE >= '2022-11-01'
),
PLAN AS (
    SELECT CAR_TYPE, 
           TRIM(TRAILING '%' FROM DISCOUNT_RATE) AS DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE DURATION_TYPE = '30일 이상'
)
SELECT CRCC.CAR_ID, 
       CRCC.CAR_TYPE, 
       CRCC.DAILY_FEE * 30 * (1 - (PLAN.DISCOUNT_RATE / 100)) AS FEE
FROM CAR_RENTAL_COMPANY_CAR AS CRCC
LEFT JOIN PLAN 
    ON CRCC.CAR_TYPE = PLAN.CAR_TYPE
WHERE CRCC.CAR_ID NOT IN (SELECT CAR_ID FROM CANNOT_RENTAL)
HAVING FEE >= 500000 AND FEE <= 2000000
ORDER BY FEE DESC, CRCC.CAR_TYPE;
