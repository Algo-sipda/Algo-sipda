select day, sum(total_bill) revenue_daily
from tips
group by day
having revenue_daily >= 1000
order by revenue_daily desc;