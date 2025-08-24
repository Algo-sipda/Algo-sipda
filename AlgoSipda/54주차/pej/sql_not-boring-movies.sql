-- [SQL] Not Boring Movies
-- https://leetcode.com/problems/not-boring-movies/

select * from cinema
where description != "boring" and id % 2 = 1
order by rating desc;