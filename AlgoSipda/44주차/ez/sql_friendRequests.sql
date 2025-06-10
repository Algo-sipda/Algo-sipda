with total as (
    select requester_id id
    from RequestAccepted
    union all
    select accepter_id id
    from RequestAccepted
)
select id, count(*) num
from total
group by id
order by num desc
limit 1;