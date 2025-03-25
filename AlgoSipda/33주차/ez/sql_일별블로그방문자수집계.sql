select event_date_kst dt, count(distinct user_pseudo_id) as users
from ga
where event_date_kst between '2021-08-02' and '2021-08-09'
group by event_date_kst
order by 1;
