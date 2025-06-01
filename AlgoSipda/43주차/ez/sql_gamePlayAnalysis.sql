with next as (
    select player_id, date_add(min(event_date), interval 1 day) next_day
    from Activity
    group by player_id
),
again as (
    select count(*) cnt
    from Activity a left join next n on a.event_date = n.next_day and a.player_id = n.player_id
    where n.next_day is not null
)

select round(cnt / count(distinct player_id), 2) fraction
from Activity, again;