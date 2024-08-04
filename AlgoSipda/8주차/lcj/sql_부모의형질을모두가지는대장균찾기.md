## Programmers 부모의 형질을 모두 가지는 대장균 찾기
### 🛠️ 문제 🛠️
```
부모의 형질을 모두 보유한 대장균의 ID(ID), 대장균의 형질(GENOTYPE), 부모 대장균의 형질(PARENT_GENOTYPE)을 출력하는 SQL 문을 작성해주세요. 이때 결과는 ID에 대해 오름차순 정렬해주세요.
```
[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/301647)

<br/>

### 💡 코드 💡
```sql
-- 부모의 형질을 모두 보유한 대장균의 IDx, GENOTYPE, PARENT_GENOTYHPE 출력
-- id 오름차순
-- bit 연산자
-- [자식의 개체 형질] & [부모의 개체 형질] = [부모의 개체 형질]
SELECT D1.ID, D1.GENOTYPE, D2.GENOTYPE AS PARENT_GENOTYPE
FROM ECOLI_DATA D1
JOIN ECOLI_DATA D2
ON D1.PARENT_ID = D2.ID
WHERE D2.GENOTYPE & D1.GENOTYPE = D2.GENOTYPE
ORDER BY D1.ID;
```

<br/>

### 📙 Mysql 개념 📙
[없음]