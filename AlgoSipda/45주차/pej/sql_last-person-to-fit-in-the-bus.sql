-- [SQL] Last Person to Fit in the Bus
-- https://leetcode.com/problems/last-person-to-fit-in-the-bus/

select person_name 
from (
    select person_name, turn, sum(weight) over(order by turn) as total_weight
    from queue
) as bus
where bus.total_weight<=1000
order by bus.total_weight desc
limit 1;