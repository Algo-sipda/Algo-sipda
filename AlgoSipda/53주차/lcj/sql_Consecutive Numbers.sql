# 연속해서 3번 이상 나온 숫자 찾기
with logsNum as (
    select 
        num, 
        lead(num, 1) over() num1, 
        lead(num, 2) over() num2
    from logs
)
select 
    distinct num ConsecutiveNums 
from 
    logsNum
where
    1 = 1
    and num = num1
    and num = num2
;