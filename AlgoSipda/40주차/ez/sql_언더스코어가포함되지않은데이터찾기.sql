select distinct page_location
from ga
where page_location not like '%\_%' escape '\'
order by 1;