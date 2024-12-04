## Programmers 레스토랑의 대목

### 🛠️ 문제 🛠️

```
tips 테이블에는 식사 금액, 팁, 결제자 성별, 요일, 시간대 등 어느 레스토랑의 테이블 당 결제에 관련된 데이터가 들어있습니다. 요일별 매출액 합계를 구하고, 매출이 1500 달러 이상인 요일의 결제 내역을 모두 출력하는 쿼리를 작성해주세요. 쿼리 결과에는 tips 테이블에 있는 모든 컬럼이 포함되어야 합니다.
```

[문제 바로가기](https://solvesql.com/problems/high-season-of-restaurant/)

<br/>

### 💡 코드 💡

```sql
-- 요일별 매출액 합계 구하기, 매출이 1500 달러 이상인 요일의 결제 내역을 모두 출력
select *
from tips
where 1 = 1
    and day in (select day
                from tips
                group by day
                having sum(total_bill) >= 1500);

```

<br/>

### 📙 개념 📙

[없음]
