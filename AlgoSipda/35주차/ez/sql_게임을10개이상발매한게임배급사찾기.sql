select distinct(name)
from companies c right join (
  select count(game_id) cnt, publisher_id
  from games
  group by publisher_id
  having cnt >= 10) g on c.company_id = g.publisher_id
;