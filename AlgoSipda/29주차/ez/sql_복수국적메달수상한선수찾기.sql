select name
from (
  select athlete_id
    from
      (
        select id
        from games
        where year >= 2000
      ) game left join records on game.id = records.game_id
    where medal is not null
    group by athlete_id
    having count(DISTINCT team_id) > 1
) a left join athletes on a.athlete_id = athletes.id
order by 1;