## Programmers 있었는데요 없었습니다
### 🛠️ 문제 🛠️
```
관리자의 실수로 일부 동물의 입양일이 잘못 입력되었습니다. 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 시작일이 빠른 순으로 조회해야합니다.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/59043)

<br/>

### 💡 코드 💡
```sql
SELECT AI.ANIMAL_ID, AI.NAME
FROM ANIMAL_INS AI
LEFT JOIN ANIMAL_OUTS AO
ON AO.ANIMAL_ID = AI.ANIMAL_ID 
WHERE AO.DATETIME <= AI.DATETIME
ORDER BY AI.DATETIME;
```

<br/>

### 📙 Mysql 개념 📙
[없음]