## Programmers 특정 세대의 대장균 찾기

### 🛠️ 문제 🛠️

```
3세대의 대장균의 ID(ID) 를 출력하는 SQL 문을 작성해주세요. 이때 결과는 대장균의 ID 에 대해 오름차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/301650)

<br/>

### 💡 코드 💡

```sql
-- 3세대의 대장균의 ID
-- 대장균의 ID에 대한 오름차순 정렬
-- 최초의 대장균 개체의 PARENT_ID == NULL
SELECT E1.ID
FROM ECOLI_DATA E1
JOIN ECOLI_DATA E2
ON E1.PARENT_ID = E2.ID
JOIN ECOLI_DATA E3
ON E2.PARENT_ID = E3.ID
WHERE E2.PARENT_ID IS NOT NULL AND E3.PARENT_ID IS NULL
ORDER BY ID ASC;
```

<br/>

### 📙 Mysql 개념 📙
[없음]
