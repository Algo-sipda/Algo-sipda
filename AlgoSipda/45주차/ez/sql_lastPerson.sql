select q.person_name
from (
    select person_name, sum(weight) over (order by turn) weight
    from Queue
    order by turn) q
where q.weight <= 1000
order by q.weight desc limit 1;