## Programmers 즐겨찾기가 가장 많은 식당 정보 출력하기
### 🛠️ 문제 🛠️
```
동물 보호소에 들어온 동물 중 이름이 Lucy, Ella, Pickle, Rogan, Sabrina, Mitty인 동물의 아이디와 이름, 성별 및 중성화 여부를 조회하는 SQL 문을 작성해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/59046)

<br/>

### 💡 코드 💡
```sql
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty');
```

<br/>

### 📙 Mysql 개념 📙
[없음]