SELECT
  classification,
  sum(
    case
      when substr(acquisition_date, 1, 4) = '2014' then 1
      else 0
    end
  ) as '2014',
  sum(
    case
      when substr(acquisition_date, 1, 4) = '2015' then 1
      else 0
    end
  ) as '2015',
  sum(
    case
      when substr(acquisition_date, 1, 4) = '2016' then 1
      else 0
    end
  ) as '2016'
from
  artworks
group by
  classification
order by
  classification;