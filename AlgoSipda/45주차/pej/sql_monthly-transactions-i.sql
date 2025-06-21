-- [SQL] Monthly Transactions I
-- https://leetcode.com/problems/monthly-transactions-i/description/
-- date_format(trans_date, '%Y-%m')

select DATE_FORMAT(trans_date, '%Y-%m') as month, 
country, count(trans_date) as trans_count, 
sum(
    case when state = "approved" then 1 else 0 end

) as approved_count, 
sum(amount) as trans_total_amount, 
sum(
    case when state="approved" then amount else 0 end

)approved_total_amount 
from Transactions 
group by  DATE_FORMAT(trans_date, '%Y-%m') , country;