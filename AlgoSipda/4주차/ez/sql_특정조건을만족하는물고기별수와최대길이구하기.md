## Pro 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기

---

### 문제
```
FISH_INFO에서 평균 길이가 33cm 이상인 물고기들을 종류별로 분류하여 잡은 수, 최대 길이, 물고기의 종류를 출력하는 SQL문을 작성해주세요. 

결과는 물고기 종류에 대해 오름차순으로 정렬해주시고, 10cm이하의 물고기들은 10cm로 취급하여 평균 길이를 구해주세요.

컬럼명은 물고기의 종류 'FISH_TYPE', 잡은 수 'FISH_COUNT', 최대 길이 'MAX_LENGTH'로 해주세요.
```

### 코드
```sql
-- 10CM이하/NULL 길이의 물고기 > 10CM로 취급
-- 물고기 종류별 평균 길이가 33CM 이상인 물고기
WITH BIG_FISH AS (
    SELECT FISH_TYPE
    FROM FISH_INFO
    GROUP BY FISH_TYPE
    HAVING AVG(IF(LENGTH < 10 OR LENGTH IS NULL, 10, LENGTH)) > 33
)

-- ORDER BY 물고기 종류
SELECT COUNT(ID) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM FISH_INFO
WHERE FISH_TYPE IN (SELECT FISH_TYPE FROM BIG_FISH)
GROUP BY FISH_TYPE
ORDER BY FISH_TYPE;
```