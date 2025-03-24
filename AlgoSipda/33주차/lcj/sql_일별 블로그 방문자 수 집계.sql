select
  event_date_kst as dt,
  count(DISTINCT user_pseudo_id) as users
from
  ga
where
  dt BETWEEN '2021-08-02' and '2021-08-09'
group by
  dt
order by
  dt