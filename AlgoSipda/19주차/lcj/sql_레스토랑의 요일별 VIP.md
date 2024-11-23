## Programmers 레스토랑의 요일별 VIP

### 🛠️ 문제 🛠️

```
tips 테이블에는 식사 금액, 팁, 결제자 성별, 요일, 시간대 등 어느 레스토랑의 테이블 당 결제에 관련된 데이터가 들어있습니다. 요일별로 가장 높은 금액의 결제 내역을 출력하는 쿼리를 작성해주세요. 쿼리 결과에는 tips 테이블에 있는 모든 컬럼이 포함되어야 합니다.
```

[문제 바로가기](https://solvesql.com/problems/restaurant-vip/)

<br/>

### 💡 코드 💡

```sql
-- 식사 금액, 팁, 결제자 성별, 요일, 시간대 등
-- 요일별로 가장 높은 금액의 결제 내역

select max(total_bill) as total_bill, tip, sex, smoker, day, time, size
from tips
group by day;

```

<br/>

### 📙 개념 📙

[없음]
