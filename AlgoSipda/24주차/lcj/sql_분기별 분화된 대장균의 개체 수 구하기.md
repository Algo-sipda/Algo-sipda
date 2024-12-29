## Programmers 분기별 분화된 대장균의 개체 수 구하기


### 🛠️ 문제 🛠️

```
각 분기(QUARTER)별 분화된 대장균의 개체의 총 수(ECOLI_COUNT)를 출력하는 SQL 문을 작성해주세요. 이때 각 분기에는 'Q' 를 붙이고 분기에 대해 오름차순으로 정렬해주세요. 대장균 개체가 분화되지 않은 분기는 없습니다.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/299308)

<br/>

### 💡 코드 💡

```sql
-- 풀이 1번
-- 각 분기(QUARTER)별 대장균의 개체의 총 수(ECOLI_COUNT)
-- 분기에는 'Q'를 붙이기
-- 1~3 / 4~6 / 7~9 / 10~12
-- 분기 기준 오름차순
WITH QUARTER_TABLE AS (
    SELECT 
    CASE
        WHEN RIGHT(DIFFERENTIATION_DATE, 5) <= "03-31" THEN 1
        WHEN RIGHT(DIFFERENTIATION_DATE, 5) <= "06-30" THEN 2
        WHEN RIGHT(DIFFERENTIATION_DATE, 5) <= "09-30" THEN 3
        WHEN RIGHT(DIFFERENTIATION_DATE, 5) <= "12-31" THEN 4
    END AS QUARTER,
    COUNT(*) AS ECOLI_COUNT
FROM
        ECOLI_DATA
GROUP BY
        QUARTER
)
SELECT
    CONCAT(QUARTER, "Q") AS QUARTER,
    ECOLI_COUNT
FROM
        QUARTER_TABLE
ORDER BY
        QUARTER;
```
```sql
-- 풀이 2번
SELECT
    CONCAT(CEIL(MONTH(DIFFERENTIATION_DATE) / 3), 'Q') AS QUARTER,
    COUNT(ID) AS ECOLI_COUNT
FROM
    ECOLI_DATA
GROUP BY
    QUARTER
ORDER BY
    QUARTER
```

<br/>

### 📙 개념 📙

[없음]
