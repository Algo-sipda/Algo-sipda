with grouped_table as (
    select
        user_id,
        sum(distance) distance_sum
    from
        rides
    group by
        user_id
)

select
    u.name,
    coalesce(g.distance_sum, 0) as travelled_distance 
from
    users u
left join
    grouped_table g
on 
    u.id = g.user_id
order by
    travelled_distance desc,
    u.name;