## Programmers 다음날도 서울숲의 미세먼지 농도는 나쁨

### 🛠️ 문제 🛠️

```
서울숲 일별 평균 대기오염도 데이터셋은 2022년 서울숲 대기오염도 측정소에서 매일 기록한 대기오염 정보를 담고 있습니다.

measurements 테이블의 pm10 컬럼에는 다양한 대기오염도 측정 기준 중에서도 미세먼지(PM10) 농도가 기록되어 있습니다. 이 데이터를 이용하여 당일의 미세먼지 농도보다 바로 다음날의 미세먼지 농도가 더 안좋은 날을 찾아주세요. 결과는 아래 컬럼들을 포함해야 합니다.

today: 당일 (YYYY-MM-DD)
next_day: 다음날 (YYYY-MM-DD)
pm10: 당일의 미세먼지 농도
next_pm10: 다음날의 미세먼지 농도
```

[문제 바로가기](https://solvesql.com/problems/bad-finedust-measure/)

<br/>

### 💡 코드 💡

```sql
SELECT M1.measured_at AS today, M2.measured_at AS next_day, M1.PM10, M2.PM10 AS next_pm10
FROM MEASUREMENTS M1
JOIN MEASUREMENTS M2
ON M1.measured_at = DATE(M2.measured_at, '-1 DAY')
WHERE M2.PM10 > M1.PM10;

```

<br/>

### 📙 개념 📙

[없음]
