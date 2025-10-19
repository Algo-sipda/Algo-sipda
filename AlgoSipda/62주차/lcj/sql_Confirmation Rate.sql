select
    s.user_id,
    round(avg(case 
            when c.action = 'confirmed' then 1
            else 0
        end
    ), 2) confirmation_rate
from 
    signups s
left join
    confirmations c
on
    s.user_id = c.user_id
group by
    s.user_id;