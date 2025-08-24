with red as (
    select s.name
    from SalesPerson s, Company c, Orders o
    where c.com_id = o.com_id and o.sales_id = s.sales_id
    and c.name = 'RED'
)

select name
from SalesPerson
where name not in (
    select name
    from red
);