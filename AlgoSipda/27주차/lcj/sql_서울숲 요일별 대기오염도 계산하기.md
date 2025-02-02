## Programmers 서울숲 요일별 대기오염도 계산하기

### 🛠️ 문제 🛠️

```
서울숲 일별 평균 대기오염도 데이터베이스에는 서울숲 대기 관측소에서 2022년 1년 동안 측정한 대기 오염 정보가 들어있습니다. 서울숲과 그 주변 일대의 유동 인구가 늘어남에 따라 서울숲 주변의 대기 오염 패턴도 유동 인구의 방문에 따라 달라지는 부분이 있을 것으로 예상되는데 이를 데이터를 통해 확인해보고자 합니다. 특히, 주말에 유동 인구가 늘어나고 월요일에 유동인구가 감소하는 방문 패턴이 대기 오염에도 영향을 미치는지 확인하려고 합니다.

이를 위해, measurements 테이블의 데이터를 조회하여 요일별 대기 오염도 평균을 계산하는 쿼리를 작성해주세요. 쿼리 결과에는 아래 7개의 컬럼이 존재해야하며, 대기 오염도 값은 모두 소수점 다섯째 자리에서 반올림 해 넷째 자리까지 표현되어야 합니다. 또한, 쿼리 결과는 월요일부터 일요일 순으로 출력되어야 합니다.

weekday: 요일 (월요일-일요일)
no2: 평균 이산화질소(NO2) 농도(ppm)
o3: 평균 오존(O3) 농도(ppm)
co: 평균 일산화탄소(CO) 농도(ppm)
so2: 평균 아황산가스 농도(ppm)
pm10: 평균 미세먼지(PM10) 농도(㎍/㎥)
pm2_5: 평균 초미세먼지(PM2.5) 농도(㎍/㎥)
```

[문제 바로가기](https://solvesql.com/problems/weekday-stats-airpollution/)

<br/>

### 💡 코드 💡

```sql
SELECT
  CASE strftime('%u', measured_at)
    WHEN '1' THEN '월요일'
    WHEN '2' THEN '화요일'
    WHEN '3' THEN '수요일'
    WHEN '4' THEN '목요일'
    WHEN '5' THEN '금요일'
    WHEN '6' THEN '토요일'
    WHEN '7' THEN '일요일'
    ELSE '알 수 없는 요일'
  END as weekday,
  round(avg(no2), 4) as no2,
  round(avg(o3), 4) as o3,
  round(avg(co), 4) as co,
  round(avg(so2), 4) as so2,
  round(avg(pm10), 4) as pm10,
  round(avg(pm2_5), 4) as pm2_5
FROM measurements
GROUP BY 1
ORDER BY strftime('%u', measured_at);
```

<br/>

### 📙 개념 📙

[없음]
