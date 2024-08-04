## Programmers 부서별 평균 연봉 조회하기
### 🛠️ 문제 🛠️
```
HR_DEPARTMENT와 HR_EMPLOYEES 테이블을 이용해 부서별 평균 연봉을 조회하려 합니다. 부서별로 부서 ID, 영문 부서명, 평균 연봉을 조회하는 SQL문을 작성해주세요.

평균연봉은 소수점 첫째 자리에서 반올림하고 컬럼명은 AVG_SAL로 해주세요.
결과는 부서별 평균 연봉을 기준으로 내림차순 정렬해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/284529)

<br/>

### 💡 코드 💡
```sql
SELECT E.DEPT_ID, D.DEPT_NAME_EN, ROUND(AVG(SAL), 0) AS AVG_SAL
FROM HR_EMPLOYEES E
JOIN HR_DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID
GROUP BY DEPT_ID
ORDER BY AVG_SAL DESC;
```

<br/>

### 📙 Mysql 개념 📙
[없음]