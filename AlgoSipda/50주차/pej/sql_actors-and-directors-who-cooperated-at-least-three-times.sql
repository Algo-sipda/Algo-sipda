-- [SQL] Students and Examinations
-- https://leetcode.com/problems/students-and-examinations/description/

# Write your MySQL query statement below
select actor_id, director_id from ActorDirector
group by actor_id, director_id
having count(distinct(timestamp)) >= 3;