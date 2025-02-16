## Programmers 잡은 물고기의 평균 길이 구하기


### 🛠️ 문제 🛠️

```
잡은 물고기의 평균 길이를 출력하는 SQL문을 작성해주세요.

평균 길이를 나타내는 컬럼 명은 AVERAGE_LENGTH로 해주세요.
평균 길이는 소수점 3째자리에서 반올림하며, 10cm 이하의 물고기들은 10cm 로 취급하여 평균 길이를 구해주세요.
```

[문제 바로가기](https://school.programmers.co.kr/learn/courses/30/lessons/293259)

<br/>

### 💡 코드 💡

```sql
SELECT ROUND(AVG(IFNULL(LENGTH, 10)),2) AS "AVERAGE_LENGTH"
FROM FISH_INFO;
```

<br/>

### 📙 개념 📙

[없음]
