select id
from (select id, max(x) from points)
union
select id
from (select id, max(y) from points)
order by 1;