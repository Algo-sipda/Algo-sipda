select name
from (
  select distinct g.name,
    case
      when p.name in ('PS3', 'PS4', 'PSP', 'PSV') then 1
      when p.name in ('Wii', 'WiiU', 'DS', '3DS') then 2
      when p.name in ('X360', 'XONE') then 3
    end major
  from games g left join platforms p on g.platform_id = p.platform_id
  where year >= 2012
)
where major is not NULL
group by 1
having count(*) >= 2;