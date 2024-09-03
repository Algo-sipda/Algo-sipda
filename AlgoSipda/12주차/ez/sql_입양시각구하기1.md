## Pro 입양 시각 구하기(1)

---

### 문제
```
09:00부터 19:59까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성해주세요. 

이때 결과는 시간대 순으로 정렬해야 합니다.
```

### 코드
```sql
SELECT HOUR(DATETIME) AS HOUR, COUNT(*) AS COUNT
FROM ANIMAL_OUTS
WHERE HOUR(DATETIME) BETWEEN '09' AND '19'
GROUP BY HOUR
ORDER BY HOUR;
```