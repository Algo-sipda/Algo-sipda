## Programmers 진료과별 총 예약 횟수 출력하기

### 🛠️ 문제 🛠️

```
APPOINTMENT 테이블에서 2022년 5월에 예약한 환자 수를 진료과코드 별로 조회하는 SQL문을 작성해주세요. 이때, 컬럼명은 '진료과 코드', '5월예약건수'로 지정해주시고 결과는 진료과별 예약한 환자 수를 기준으로 오름차순 정렬하고, 예약한 환자 수가 같다면 진료과 코드를 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/132202)

<br/>

### 💡 코드 💡

```sql
-- 2022년 5월
-- 진료과별 예약한 환자 수를 기준 오름차순
-- 같다면 진료과 코드를 기준으로 오름차순
SELECT MCDP_CD AS '진료과 코드', COUNT(*) AS '5월예약건수'
FROM APPOINTMENT
WHERE YEAR(APNT_YMD) = '2022' AND MONTH(APNT_YMD) = '05'
GROUP BY MCDP_CD
ORDER BY 5월예약건수, MCDP_CD;
```

<br/>

### 📙 개념 📙

[없음]
