select day, sum(total_bill) as revenue_daily  from tips 
group by day
having sum(total_bill)>= 1000;