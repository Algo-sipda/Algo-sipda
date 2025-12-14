# 자신의 기사를 최소 한번이라도 본 저자
select 
    distinct author_id as id
from
    views
where
    author_id = viewer_id
order by
    id;