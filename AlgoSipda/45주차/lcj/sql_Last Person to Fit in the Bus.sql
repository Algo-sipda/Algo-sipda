with sum_weight_table as (
    select *, sum(weight) over (order by turn) as total_weight
    from queue
)

select person_name
from sum_weight_table
where total_weight <= 1000
order by total_weight desc
limit 1;