select order_date,
  count(distinct case when category = 'Furniture' then order_id end) "furniture",
  round(count(distinct case when category = 'Furniture' then order_id end) / (count(distinct order_id) + 0.00) * 100, 2) "furniture_pct"
from records
group by order_date
having count(distinct order_id) >= 10 and furniture_pct >= 40
order by 3 desc, 1;