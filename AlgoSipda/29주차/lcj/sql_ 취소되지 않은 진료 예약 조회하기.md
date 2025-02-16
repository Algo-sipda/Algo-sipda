## Programmers 취소되지 않은 진료 예약 조회하기


### 🛠️ 문제 🛠️

```
PATIENT, DOCTOR 그리고 APPOINTMENT 테이블에서 2022년 4월 13일 취소되지 않은 흉부외과(CS) 진료 예약 내역을 조회하는 SQL문을 작성해주세요. 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시 항목이 출력되도록 작성해주세요. 결과는 진료예약일시를 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/132204)

<br/>

### 💡 코드 💡

```sql
SELECT AP.APNT_NO, P.PT_NAME, AP.PT_NO, AP.MCDP_CD, D.DR_NAME, AP.APNT_YMD
FROM APPOINTMENT AP 
JOIN DOCTOR D 
ON D.DR_ID = AP.MDDR_ID
JOIN PATIENT P 
ON P.PT_NO = AP.PT_NO
WHERE 1 = 1
    AND AP.APNT_CNCL_YMD IS NULL 
    AND AP.MCDP_CD = 'CS' 
    AND AP.APNT_YMD LIKE '2022-04-13%'
ORDER BY AP.APNT_YMD;
```

<br/>

### 📙 개념 📙

[없음]
