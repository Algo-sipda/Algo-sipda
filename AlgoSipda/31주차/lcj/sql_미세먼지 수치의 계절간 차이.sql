select
  CASE
    WHEN substr(measured_at, 6, 2) IN ('03', '04', '05') THEN 'spring'
    WHEN substr(measured_at, 6, 2) IN ('06', '07', '08') THEN 'summer'
    WHEN substr(measured_at, 6, 2) IN ('09', '10', '11') THEN 'autumn'
    ELSE 'winter'
  END as season,
  median (pm10) as pm10_median,
  round(avg(pm10), 2) as pm10_average
from
  measurements
group by
  season;