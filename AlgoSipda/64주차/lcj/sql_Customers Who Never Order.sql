select
    c.name as 'Customers'
from 
    customers c
where
    1 = 1
    and c.id not in (
                        select customerid
                        from orders
                    );