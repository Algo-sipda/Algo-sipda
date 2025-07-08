with d as (
    select *, row_number() over (order by id desc) + id as diff
    from Stadium
    where people >= 100
    order by id
)
select id, visit_date, people
from d
where diff in (select diff from d group by diff having count(*) >= 3)
order by visit_date;