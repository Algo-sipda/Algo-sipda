select
  order_date,
  count(
    distinct (
      case
        when category = 'Furniture' then order_id
      end
    )
  ) as furniture,
  round(
    (
      count(
        distinct (
          case
            when category = 'Furniture' then order_id
          end
        )
      ) + 0.00
    ) / (count(distinct (order_id)) + 0.00) * 100,
    2
  ) as furniture_pct
from
  records
group by
  order_date
having
  1 = 1
  and count(distinct order_id) >= 10
  and furniture_pct >= 40
order by
  furniture_pct desc;