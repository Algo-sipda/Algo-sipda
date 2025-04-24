select *
from tips
group by day
having total_bill = max(total_bill);