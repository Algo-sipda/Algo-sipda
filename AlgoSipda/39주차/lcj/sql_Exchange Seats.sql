# Write your MySQL query statement below
select case 
            when mod(id, 2) = 1  and id = (select max(id) from seat) then id # 마지막 줄
            when mod(id, 2) = 0 then id-1 # id 값이 짝수인 경우
            else id + 1 # 나머지
        end id,
        student
from seat
order by id;