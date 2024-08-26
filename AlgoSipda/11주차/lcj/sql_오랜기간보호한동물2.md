## Programmers 오랜 기간 보호한 동물(2)

### 🛠️ 문제 🛠️

```
입양을 간 동물 중, 보호 기간이 가장 길었던 동물 두 마리의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 기간이 긴 순으로 조회해야 합니다.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/59411)

<br/>

### 💡 코드 💡

```sql
-- 입양을 간 동물 중 -> 보호 기간이 가장 길었던 동물 두 마리의 아이디, 이름
-- 보호 기간이 긴 순으로 조회
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I
JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY DATEDIFF(O.DATETIME, I.DATETIME) DESC
LIMIT 2;
```

<br/>

### 📙 Mysql 개념 📙

[없음]
