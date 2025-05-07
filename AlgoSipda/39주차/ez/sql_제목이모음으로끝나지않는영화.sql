select title
from film
where rating in ('R', 'NC-17')
and substr(title, -1, 1) not in ('A', 'E', 'I', 'O', 'U');