## Programmers 고양이와 개는 몇 마리 있을까
### 🛠️ 문제 🛠️
```
동물 보호소에 들어온 동물 중 고양이와 개가 각각 몇 마리인지 조회하는 SQL문을 작성해주세요. 이때 고양이를 개보다 먼저 조회해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/59040)

<br/>

### 💡 코드 💡
```sql
SELECT ANIMAL_TYPE, COUNT(*) count
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE;
```

<br/>

### 📙 Mysql 개념 📙
[없음]