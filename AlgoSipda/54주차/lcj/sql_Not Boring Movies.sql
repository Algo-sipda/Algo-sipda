# 홀수 ID 그리고 "boring"이 아닌 것
# rating 내림차순
select 
    *
from
    cinema
where
    1 = 1
    and MOD(id,2) = 1
    and description != "boring"
order by
    rating desc;