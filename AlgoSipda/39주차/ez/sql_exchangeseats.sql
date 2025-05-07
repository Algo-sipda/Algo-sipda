select
    case
        when id % 2 = 1 and id != (select max(id) from seat) then id + 1
        when id % 2 = 0 then id - 1
        else id
    end id, student
from seat
order by 1;