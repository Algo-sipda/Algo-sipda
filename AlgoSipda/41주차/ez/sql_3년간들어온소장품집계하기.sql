select classification,
  sum(case when substr(acquisition_date, 1, 4) = '2014' then 1 else 0 end) '2014',
  sum(case when substr(acquisition_date, 1, 4) = '2015' then 1 else 0 end) '2015',
  sum(case when substr(acquisition_date, 1, 4) = '2016' then 1 else 0 end) '2016'
from artworks
group by 1
order by 1;