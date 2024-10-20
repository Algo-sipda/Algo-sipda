## Programmers 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기

### 🛠️ 문제 🛠️

```
HR_DEPARTMENT, HR_EMPLOYEES, HR_GRADE 테이블을 이용해 사원별 성과금 정보를 조회하려합니다. 평가 점수별 등급과 등급에 따른 성과금 정보가 아래와 같을 때, 사번, 성명, 평가 등급, 성과금을 조회하는 SQL문을 작성해주세요.

평가등급의 컬럼명은 GRADE로, 성과금의 컬럼명은 BONUS로 해주세요.
결과는 사번 기준으로 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/284528)

<br/>

### 💡 코드 💡

```sql
SELECT E.EMP_NO
        , E.EMP_NAME
        , (CASE
            WHEN AVG(SCORE) >= 96 THEN 'S'
            WHEN AVG(SCORE) >= 90 THEN 'A'
            WHEN AVG(SCORE) >= 80 THEN 'B'
            ELSE 'C'
        END) AS GRADE
        , (CASE
            WHEN AVG(SCORE) >= 96 THEN SAL * 0.2
            WHEN AVG(SCORE) >= 90 THEN SAL * 0.15
            WHEN AVG(SCORE) >= 80 THEN SAL * 0.1
            ELSE 0
          END) AS BONUS
FROM HR_EMPLOYEES E
JOIN HR_GRADE G
ON E.EMP_NO = G.EMP_NO
GROUP BY E.EMP_NO
ORDER BY E.EMP_NO;
```

<br/>

### 📙 개념 📙

[없음]
