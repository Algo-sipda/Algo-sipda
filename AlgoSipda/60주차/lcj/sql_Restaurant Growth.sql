select 
    visited_on, 
    amount, 
    round(amount/7,2) as average_amount
from (
    select 
        distinct visited_on, 
        sum(amount) over (order by visited_on range between interval '6' day preceding and current row) as amount,
        min(visited_on) over() as first_date
    from 
        customer
) a
where 
    visited_on >= first_date + 6;