with total as (
  select user_a_id user_id, count(*) num_friends
  from edges
  group by user_a_id
  union all
  select user_b_id user_id, count(*) num_friends
  from edges
  group by user_b_id
)

select u.user_id, ifnull(sum(num_friends), 0) num_friends
from users u left join total t on u.user_id = t.user_id
group by u.user_id
order by 2 desc, 1;