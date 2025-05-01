-- 각 사용자의 친구 수를 집계해 출력
-- 친구 수가 많은 사용자부터 출력되어야 함
-- 만약 친구 수가 같은 사용자가 여럿 -> 사용자 id 작은 순
with
  f1 as (
    select
      *
    from
      edges
    union all
    select
      user_b_id as user_a_id,
      user_a_id as user_b_id
    from
      edges
  ),
  f2 as (
    select
      *
    from
      f1
    group by
      user_a_id,
      user_b_id
  )
select
  u.user_id,
  count(f2.user_b_id) as num_friends
from
  users u
  left join f2 on u.user_id = f2.user_a_id
group by
  u.user_id
order by
  num_friends desc,
  u.user_id;