## Programmers 
FrontEnd 개발자 찾기

### 🛠️ 문제 🛠️

```
DEVELOPERS 테이블에서 Front End 스킬을 가진 개발자의 정보를 조회하려 합니다. 조건에 맞는 개발자의 ID, 이메일, 이름, 성을 조회하는 SQL 문을 작성해 주세요.

결과는 ID를 기준으로 오름차순 정렬해 주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/276035)

<br/>

### 💡 코드 💡

```sql
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS D, SKILLCODES S
WHERE 1 = 1
    AND S.CATEGORY = 'Front End'
    AND (D.SKILL_CODE & S.CODE) > 0
GROUP BY ID, EMAIL, FIRST_NAME, LAST_NAME
ORDER BY ID;

```

<br/>

### 📙 개념 📙

[없음]
