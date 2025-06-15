select date_format(trans_date, '%Y-%m') month, country, count(*) trans_count, sum(if(state = 'approved', 1, 0)) approved_count, sum(amount) trans_total_amount, sum(if(state = 'approved', amount, 0)) approved_total_amount
from Transactions
group by date_format(trans_date, '%Y-%m'), country;