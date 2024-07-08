## Pro 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기

---

### 문제
```
CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블에서 
대여 시작일을 기준으로 2022년 8월부터 2022년 10월까지 총 대여 횟수가 5회 이상인 자동차들에 대해서 
해당 기간 동안의 월별 자동차 ID 별 총 대여 횟수(컬럼명: RECORDS) 리스트를 출력하는 SQL문을 작성해주세요.

결과는 월을 기준으로 오름차순 정렬하고, 월이 같다면 자동차 ID를 기준으로 내림차순 정렬해주세요.

특정 월의 총 대여 횟수가 0인 경우에는 결과에서 제외해주세요.
```

### 코드
```sql
-- 시작일이 2022년 8월부터 10월까지 내역 > BORROW
-- 총 대여 횟수가 5회 이상인 자동차 ID > BORROW_CNT
WITH BORROW AS (
    SELECT MONTH(START_DATE) AS MONTH, CAR_ID, HISTORY_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE YEAR(START_DATE) = 2022 AND MONTH(START_DATE) BETWEEN 8 AND 10
), BORROW_CNT AS (
    SELECT CAR_ID
    FROM BORROW
    GROUP BY CAR_ID
    HAVING COUNT(HISTORY_ID) >= 5
)

-- WHERE 대여 횟수 5회 이상
-- ORDER BY 월, 자동차 ID
-- HAVING 월의 총 대여 횟수가 0 초과
SELECT MONTH, CAR_ID, COUNT(HISTORY_ID) AS RECORDS
FROM BORROW
WHERE CAR_ID IN (SELECT CAR_ID FROM BORROW_CNT)
GROUP BY MONTH, CAR_ID
HAVING COUNT(HISTORY_ID) > 0
ORDER BY MONTH, CAR_ID DESC;
```