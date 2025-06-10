with friends as (
    select requester_id as id, count(*) as num
    from RequestAccepted
    group by id

    union all

    select accepter_id as id, count(*) as num
    from RequestAccepted
    group by id
)
select id, sum(num) as num
from friends
group by id
order by num desc
limit 1;