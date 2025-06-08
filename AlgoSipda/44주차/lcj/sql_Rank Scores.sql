select score, dense_rank() over (order by score desc) 'rank'
from Scores
order by score desc;