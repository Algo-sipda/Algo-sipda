-- [SQL] queries-quality-and-percentage 
-- https://leetcode.com/problems/queries-quality-and-percentage/

select 
    query_name,
    round(sum(rating/position)/count(*), 2) as quality, 
    round(sum(case when rating < 3 then 1 else 0 end) * 100 / count(rating), 2) as poor_query_percentage
    from Queries
    group by query_name;
