# 가장 많은 평가한 사용자의 이름 -> 평가 수 => 사용자 이름 DESC
# 그 중에서도 평균 평점이 가장 높은 영화 제목(2020년 2월) => 영화 제목 DESC
(select
    u.name results
from 
    MovieRating mr
join
    Users u
on 
    mr.user_id = u.user_id
group by
    u.user_id
order by
    count(*) desc, u.name
limit 1)

union all

(select
    m.title results
from 
    MovieRating mr
join
    Movies m
on 
    mr.movie_id = m.movie_id
where
    mr.created_at like '2020-02-%'
group by
    m.movie_id
order by
    avg(rating) desc, m.title
limit 1);