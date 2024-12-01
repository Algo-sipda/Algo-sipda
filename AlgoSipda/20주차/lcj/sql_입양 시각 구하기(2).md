## Programmers 입양 시각 구하기(2)

### 🛠️ 문제 🛠️

```
보호소에서는 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 합니다. 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성해주세요. 이때 결과는 시간대 순으로 정렬해야 합니다.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/59413)

<br/>

### 💡 코드 💡

```sql
-- 보호소에서는 몇시에 입양이 가장 활발하게 일어나는지
-- 0시 ~ 23시까지
-- 입양 몇건 발생했는지 조회
SET @HOUR = -1;

SELECT @HOUR := @HOUR+1 AS HOUR,
        (SELECT COUNT(*)
        FROM ANIMAL_OUTS
        WHERE HOUR(DATETIME) = @HOUR) AS COUNT
FROM ANIMAL_OUTS
WHERE @HOUR+1 < 24;

```

<br/>

### 📙 개념 📙

1. 변수 생성
- set @[변수 이름] := 값;
- set 이외의 명령문에서는 "="이 비교 연산자로 취급되지 않는다.
- set @hour = -1;
- hour라는 변수를 -1로 초기화 하여 사용한다.
- 값을 선언하고 대입할 때는 ":="를 사용해야 한다.
