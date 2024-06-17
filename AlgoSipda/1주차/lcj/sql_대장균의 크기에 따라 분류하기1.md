## Programmers 대장균의 크기에 따라 분류하기
### 🛠️ 문제 🛠️
```
대장균 개체의 크기가 100 이하라면 'LOW', 100 초과 1000 이하라면 'MEDIUM', 1000 초과라면 'HIGH' 라고 분류합니다. 대장균 개체의 ID(ID) 와 분류(SIZE)를 출력하는 SQL 문을 작성해주세요.이때 결과는 개체의 ID 에 대해 오름차순 정렬해주세요.
```

### 💡 코드 💡
```sql
SELECT ID, 
    CASE 
        WHEN SIZE_OF_COLONY<=100 THEN "LOW"
        WHEN SIZE_OF_COLONY<=1000 THEN "MEDIUM"
        ELSE "HIGH"
    END AS SIZE
FROM ECOLI_DATA;
```
```sql
SELECT ID, 
    IF(SIZE_OF_COLONY<=100,
        "LOW",
        IF(SIZE_OF_COLONY<=1000,
            "MEDIUM",
            "HIGH"))
     AS SIZE
FROM ECOLI_DATA;
```
<br/>

### 📙 Mysql 개념 📙
1. If ~ Else 조건문
- 엑셀에서의 IF함수와 동일
```sql
if(조건문, 참일 때 값, 거짓일 때 값)
SELECT IF(required, '필수', '선택') AS '필수여부' FROM TABLE
```
2. Case ~ When 조건문
```sql
CASE
    WHEN 조건1 THEN '조건1 반환값'
    WHEN 조건2 THEN '조건2 반환값'
    ELSE '충족되는 조건 없을 때 반환값'
END
```