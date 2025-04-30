select
  id
from
  points
where
  1 = 1
  and x = (
    select
      MAX(x)
    FROM
      points
  )
  or y = (
    select
      MAX(y)
    from
      points
  )
order by
  id;