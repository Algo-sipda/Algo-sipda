with cte_1 as (
select rent_station_id,
sum(case  when strftime('%Y-%m',rent_at) ='2018-10' then 1 else 0 end) as rent_2018,
sum(case  when strftime('%Y-%m',rent_at) ='2019-10' then 1 else 0 end) as rent_2019
from rental_history
group by rent_station_id ),

cte_2 as (
select return_station_id,
sum(case  when strftime('%Y-%m',return_at) ='2018-10' then 1 else 0 end) as return_2018,
sum(case  when strftime('%Y-%m',return_at) ='2019-10' then 1 else 0 end) as return_2019
from rental_history
group by return_station_id ),

cte_3 as (
select rent_station_id as station_id, round((return_2019+rent_2019)*100.0/(rent_2018+return_2018),2) as usage_pct
from cte_1 c1 join cte_2 c2 on c1.rent_station_id = c2.return_station_id
where (return_2019 + rent_2019) >0 and (return_2018 + rent_2018) >0)

select s.station_id, name, local, usage_pct
from cte_3 c3 join station s on c3.station_id = s.station_id
where usage_pct <= 50;