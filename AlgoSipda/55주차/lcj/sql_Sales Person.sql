select
    sp.name
from
    orders s
join
    company c
on 
    s.com_id = c.com_id
    and c.name = 'RED'
right join
    salesperson sp
on
    sp.sales_id = s.sales_id
where
    s.sales_id is null;