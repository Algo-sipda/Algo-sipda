-- [SQL] Consecutive Numbers
-- https://leetcode.com/problems/consecutive-numbers/
-- 1번id  = 2번id -1 = 3번 아이디 -2
SELECT distinct(l1.num) as ConsecutiveNums
FROM
    Logs l1,
    Logs l2,
    Logs l3
WHERE
    l1.id = l2.id - 1
    AND l2.id = l3.id - 1
    AND l1.num = l2.num
    AND l2.num = l3.num
;