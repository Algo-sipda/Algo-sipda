## Programmers 레스토랑 웨이터의 팁 분석


### 🛠️ 문제 🛠️

```
tips 테이블에는 식사 주문 금액, 팁, 결제자 성별, 요일, 시간대 등 음식점의 방문 고객들과 관련된 데이터가 들어있습니다.

음식점의 요일, 시간대 별 패턴을 분석해보고자 합니다. 구체적으로는 각 요일의 시간대별 평균 팁은 얼마인지, 평균 일행 수는 몇 명인지 확인하고자 합니다. 평균 팁과 평균 일행 수는 소수점 아래 셋째 자리에서 반올림 해 둘째 자리까지 출력하고, 결과 데이터가 요일, 시간대의 알파벳 순으로 정렬되도록 쿼리를 작성해주세요. 결과 데이터에는 아래 4개의 컬럼이 들어가야 합니다.

day - 요일
time - 시간대
avg_tip - 평균 팁
avg_size - 평균 일행 수
```

[문제 바로가기](https://solvesql.com/problems/tip-analysis/)

<br/>

### 💡 코드 💡

```sql
SELECT
  day,
  time,
  round(avg(tip), 2) avg_tip,
  round(avg(size), 2) avg_size
FROM
  tips
GROUP BY
  day,
  time
ORDER BY
  day,
  time;
```

<br/>

### 📙 개념 📙

[없음]
