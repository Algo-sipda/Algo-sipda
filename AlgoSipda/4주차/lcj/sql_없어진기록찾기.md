## Programmers 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기
### 🛠️ 문제 🛠️
```
천재지변으로 인해 일부 데이터가 유실되었습니다. 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물의 ID와 이름을 ID 순으로 조회하는 SQL문을 작성해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/59042)

<br/>

### 💡 코드 💡
```sql
SELECT AO.ANIMAL_ID, AO.NAME
FROM ANIMAL_OUTS AO
LEFT JOIN ANIMAL_INS AI
ON AO.ANIMAL_ID = AI.ANIMAL_ID
WHERE AI.ANIMAL_ID IS NULL
ORDER BY AO.ANIMAL_ID, AO.NAME;
```

<br/>

### 📙 Mysql 개념 📙
[없음]