## Programmers 물고기 종류 별 잡은 수 구하기


### 🛠️ 문제 🛠️

```
FISH_NAME_INFO에서 물고기의 종류 별 물고기의 이름과 잡은 수를 출력하는 SQL문을 작성해주세요.

물고기의 이름 컬럼명은 FISH_NAME, 잡은 수 컬럼명은 FISH_COUNT로 해주세요.
결과는 잡은 수 기준으로 내림차순 정렬해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/293257)

<br/>

### 💡 코드 💡

```sql
SELECT COUNT(*) AS FISH_COUNT, B.FISH_NAME
FROM FISH_INFO AS A 
INNER JOIN FISH_NAME_INFO AS B
ON A.FISH_TYPE = B.FISH_TYPE
GROUP BY A.FISH_TYPE, B.FISH_NAME
ORDER BY FISH_COUNT DESC;
```

<br/>

### 📙 개념 📙

[없음]
