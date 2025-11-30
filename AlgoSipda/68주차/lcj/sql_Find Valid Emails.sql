select
    user_id,
    email
from
    users
where 1 = 1
    and email regexp '^[A-Za-z0-9_]+@[A-Za-z]+\\.com$'
order by
    user_id;