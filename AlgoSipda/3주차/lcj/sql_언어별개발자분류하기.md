## Programmers 언어별 개발자 분류하기
### 🛠️ 문제 🛠️
```
DEVELOPERS 테이블에서 GRADE별 개발자의 정보를 조회하려 합니다. GRADE는 다음과 같이 정해집니다.

A : Front End 스킬과 Python 스킬을 함께 가지고 있는 개발자
B : C# 스킬을 가진 개발자
C : 그 외의 Front End 개발자
GRADE가 존재하는 개발자의 GRADE, ID, EMAIL을 조회하는 SQL 문을 작성해 주세요.

결과는 GRADE와 ID를 기준으로 오름차순 정렬해 주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/276036)

<br/>

### 💡 코드 💡
```sql
SELECT (
        CASE
            WHEN (SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY="Front End")
                    AND SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = "Python")) THEN "A"
            WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = "C#") ThEN "B"
            WHEN SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY="Front End") THEN "C"
            ELSE NULL
        END
        ) AS GRADE
        ,ID, EMAIL
FROM DEVELOPERS
GROUP BY GRADE, ID, EMAIL
HAVING GRADE IS NOT NULL
ORDER BY GRADE, ID;
```
- 다른 사람 풀이
```sql
with FRONT AS (
    SELECT SUM(CODE)
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
)

SELECT
    CASE 
        WHEN SKILL_CODE & (SELECT * FROM FRONT) 
            AND SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'python')
        THEN 'A'
        
        WHEN SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#')
        THEN 'B'
        
        WHEN SKILL_CODE & (SELECT * FROM FRONT)
        THEN 'C'
    END AS `GRADE`, 
    ID,
    EMAIL
FROM
    DEVELOPERS
HAVING
    GRADE IS NOT NULL
ORDER BY GRADE, ID;
```

<br/>

### 📙 Mysql 개념 📙
0. sql 순서 및 HAVING은 언제 필터링?
- FROM, JOIN -> ON, WHERE -> GROUP 관련 -> SELECT
- MYSQL에서는 예외적으로 처리해줌
- 하지만, 다른 DBMS에서 불가능할 수 있으니 가급적이면 사용하지 않고 문제를 풀어봐야 함.

1. With 절
- 서브쿼리를 만들고 재사용 가능한 공동 테이블 표현식(Common Table Expression, CTE)
    - 쿼리 단순화, 가독성 증가
    - 반환된 data를 단일 쿼리에서 재사용 가능
```sql
WITH [cte_name] AS (
    SELECT [COLUMN_NAME(S)]
    FROM [TABLE_NAME]
    WHERE [CONDITION]
)

SELECT [COLUMN_NAMES(S)]
FROM [TABLE_NAME]
JOIN [CTE_NAME] ON [JOIN_CONDITION]
WHERE [CONDITION]
```

- WITH절 FRONT END 언어 코드의 합을 저장
- GRADE의 각 경우의 수에 활용해버리기