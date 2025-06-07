-- leetcode 
-- https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/description/

-- 324ms 44.88%
select tab.id as id, count(tab.id) as num
    from (
        select requester_id as id from RequestAccepted
        union all
        select accepter_id as id from RequestAccepted
    ) as tab
group by tab.id
order by count(tab.id) desc
limit 1
;
