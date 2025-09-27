# 마지막 날짜 기준 2019년 7월 27일 기준으로 30일 이내에 최소 한가지 이상 활동한 사람
select
    activity_date day,
    count(distinct user_id) active_users
from
    activity
where
    activity_date between date_add('2019-07-27', interval -29 day) and '2019-07-27'
group by
    activity_date;