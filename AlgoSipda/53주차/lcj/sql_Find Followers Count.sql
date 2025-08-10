# 각 사용자에 대해 팔로워 하는 수
# user_id 오름차순 정렬
select 
    user_id, 
    count(follower_id) followers_count 
from 
    followers
group by 
    user_id
order by 
    user_id;