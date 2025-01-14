## Programmers 할부는 몇 개월로 해드릴까요


### 🛠️ 문제 🛠️

```
olist_order_payments_dataset 테이블에는 각 주문의 결제 정보가 저장되어 있습니다.

고객이 Olist 상점에서 신용카드로 주문한 내역을 할부 개월 수 별로 나누어 살펴보려고 합니다. 할부 개월 수 별로 주문 수, 최소 결제 금액, 최대 결제 금액, 평균 결제 금액을 집계해주세요. 결과 데이터에는 5개의 컬럼이 들어가야 합니다.

payment_installments - 할부 개월 수
order_count - 주문 수
min_value - 최소 결제 금액
max_value - 최대 결제 금액
avg_value - 평균 결제 금액
```

[문제 바로가기](https://solvesql.com/problems/installment-month/)

<br/>

### 💡 코드 💡

```sql
-- 신용카드로 주문!
-- 할부 개월 수 별로 주문 수,최소 결제 금액, 최대 결제 금액, 평균 결제 금액
select
  payment_installments,
  count(distinct order_id) as order_count,
  min(payment_value) as min_value,
  max(payment_value) as max_value,
  avg(payment_value) as avg_value
from
  olist_order_payments_dataset
where
  payment_type = 'credit_card'
group by
  payment_installments;
```

<br/>

### 📙 개념 📙

[없음]
