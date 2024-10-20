## Programmers 물고기 종류 별 대어 찾기

### 🛠️ 문제 🛠️

```
물고기 종류 별로 가장 큰 물고기의 ID, 물고기 이름, 길이를 출력하는 SQL 문을 작성해주세요.

물고기의 ID 컬럼명은 ID, 이름 컬럼명은 FISH_NAME, 길이 컬럼명은 LENGTH로 해주세요.
결과는 물고기의 ID에 대해 오름차순 정렬해주세요.
단, 물고기 종류별 가장 큰 물고기는 1마리만 있으며 10cm 이하의 물고기가 가장 큰 경우는 없습니다.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/293261)

<br/>

### 💡 코드 💡

```sql
-- MySQL
SELECT ID, FISH_NAME, LENGTH
FROM FISH_INFO FI
JOIN FISH_NAME_INFO FNI
ON FI.FISH_TYPE = FNI.FISH_TYPE
WHERE 1 = 1
        AND (FI.FISH_TYPE, LENGTH) IN (SELECT FISH_TYPE, MAX(LENGTH)
                                    FROM FISH_INFO
                                   GROUP BY FISH_TYPE)
ORDER BY ID;

```

<br/>

### 📙 개념 📙

[없음]
