SELECT day, sum(total_bill) as revenue_daily
FROM tips
group by day
having revenue_daily >= 1000
order by revenue_daily desc;