select round(
  100.0 * (select count(distinct artwork_id)
  from artworks
  where credit like '%gift%')
  / count(distinct artwork_id), 3) as ratio
from artworks;