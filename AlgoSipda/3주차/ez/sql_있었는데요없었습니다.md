## Pro 있었는데요 없었습니다

---

### 문제
```
보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 

이때 결과는 보호 시작일이 빠른 순으로 조회해야합니다.
```

### 코드
```sql
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I, ANIMAL_OUTS O
WHERE I.ANIMAL_ID = O.ANIMAL_ID
  AND I.DATETIME > O.DATETIME
ORDER BY I.DATETIME ASC;
```