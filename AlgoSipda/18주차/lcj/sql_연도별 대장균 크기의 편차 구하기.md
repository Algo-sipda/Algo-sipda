## Programmers 연도별 대장균 크기의 편차 구하기

### 🛠️ 문제 🛠️

```
분화된 연도(YEAR), 분화된 연도별 대장균 크기의 편차(YEAR_DEV), 대장균 개체의 ID(ID) 를 출력하는 SQL 문을 작성해주세요. 분화된 연도별 대장균 크기의 편차는 분화된 연도별 가장 큰 대장균의 크기 - 각 대장균의 크기로 구하며 결과는 연도에 대해 오름차순으로 정렬하고 같은 연도에 대해서는 대장균 크기의 편차에 대해 오름차순으로 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/299310)

<br/>

### 💡 코드 💡

```sql
-- 분화된 연도, 분화된 연도별 대장균 크기의 변차, 대장균 개체의 ID(YEAR, YEAR_DEV, ID)
-- 분화된 연도별 대장균 크기의 편차 = 분화된 연도별 가장 큰 대장균의 크기 - 각 대장균의 크기
-- 연도에 따른 오름차순, 대장균 크기의 오름차순
WITH MAX_SIZE_OF_COLONY AS (
    SELECT MAX(SIZE_OF_COLONY) AS MAX_SIZE_OF_COLONY, YEAR(DIFFERENTIATION_DATE) AS YEAR
    FROM ECOLI_DATA
    GROUP BY YEAR
)

SELECT MS.YEAR
        , (MS.MAX_SIZE_OF_COLONY - ED.SIZE_OF_COLONY) AS YEAR_DEV
        , ID
FROM ECOLI_DATA ED, MAX_SIZE_OF_COLONY MS
WHERE YEAR(DIFFERENTIATION_DATE) = MS.YEAR
ORDER BY MS.YEAR, YEAR_DEV;


```

<br/>

### 📙 개념 📙

[없음]
