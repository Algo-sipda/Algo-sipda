select query_name, round(sum(rating / position) / count(*), 2) quality, round(sum(rating < 3) / count(*) * 100, 2) poor_query_percentage
from Queries
group by query_name;