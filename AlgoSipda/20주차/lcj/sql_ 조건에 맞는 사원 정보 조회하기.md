## Programmers 조건에 맞는 사원 정보 조회하기

### 🛠️ 문제 🛠️

```
HR_DEPARTMENT, HR_EMPLOYEES, HR_GRADE 테이블에서 2022년도 한해 평가 점수가 가장 높은 사원 정보를 조회하려 합니다. 2022년도 평가 점수가 가장 높은 사원들의 점수, 사번, 성명, 직책, 이메일을 조회하는 SQL문을 작성해주세요.

2022년도의 평가 점수는 상,하반기 점수의 합을 의미하고, 평가 점수를 나타내는 컬럼의 이름은 SCORE로 해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/284527)

<br/>

### 💡 코드 💡

```sql
-- 2022년 한해 평가 점수가 가장 높은 사원 정보 조회
-- 2022년도 평가 점수가 가장 높은 사원들의 점수, 사번, 성명, 직책, 이메일
-- 2022년도의 평가 점수 = 상 + 하반기 점수의 합 => score
select SUM(HG.SCORE) AS SCORE, HG.EMP_NO, HE.EMP_NAME, HE.POSITION, HE.EMAIL
from HR_EMPLOYEES HE
JOIN HR_GRADE HG
ON HE.EMP_NO = HG.EMP_NO
GROUP BY HG.YEAR, HE.EMP_NO
HAVING HG.YEAR = 2022
ORDER BY SCORE DESC
LIMIT 1;
```

<br/>

### 📙 개념 📙

[없음]
