select (
  CASE
    WHEN measured_at BETWEEN '2022-03-01' AND '2022-05-31' THEN 'spring'
    WHEN measured_at BETWEEN '2022-06-01' AND '2022-08-31' THEN 'summer'
    WHEN measured_at BETWEEN '2022-09-01' AND '2022-11-30' THEN 'autumn'
  ELSE
    'winter'
  END
) season, median(pm10) pm10_median, round(avg(pm10), 2) pm10_average
from measurements
group by season;
