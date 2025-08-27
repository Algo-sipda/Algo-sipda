select s.name
from salesperson s
where not exists (
  select 1
  from orders o
  join company c on c.com_id = o.com_id
  where o.sales_id = s.sales_id
    and c.name = 'RED'
);