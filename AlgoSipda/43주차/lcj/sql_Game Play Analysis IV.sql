# 첫 로그인일로부터 최소 이틀 연속 로그인한 플레이어 수
with cte_login as (
    select a1.player_id, datediff(a1.event_date, first_login_date) = 1 as login
    from activity a1
    join (
        select player_id, min(event_date) as first_login_date
        from activity 
        group by player_id
    ) a2
    on a1.player_id = a2.player_id
)
select round(sum(login) / count(distinct player_id), 2) as fraction
from cte_login;