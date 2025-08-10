with Next as (
select id, num, lag(num) over(order by id) next
from Logs
),
nextNext as (
    select id, num, next, lag(next) over(order by id) nextnext
    from Next
)

select distinct(num) ConsecutiveNums
from nextNext n
where num = next and next = nextnext;