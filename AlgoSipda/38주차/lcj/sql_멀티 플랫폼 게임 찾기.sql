-- 2012년 이후 출시된 게임들 중
-- 둘 이상의 메이저 플랫폼 계열에 출시된 게임 이름을 출력하는 쿼리
-- 중복된 게임은 1번만 출력되어야 함
with
  affiliate as (
    select
      *,
      case
        when name in ('PS3', 'PS4', 'PSP', 'PSV') then 'Sony'
        when name in ('Wii', 'WiiU', 'DS', '3DS') then 'Nintendo'
        when name in ('X360', 'XONE') then 'Microsoft'
        else 'Others'
      end as category
    from
      platforms
  ),
  majorPlatforms as (
    select
      *
    from
      games g
      right join affiliate a on g.platform_id = a.platform_id
    where
      1 = 1
      and year >= 2012
      and category not in('Others')
  )
select DISTINCT
  name
from
  majorPlatforms
group by
  name
having
  count(distinct category) >= 2;