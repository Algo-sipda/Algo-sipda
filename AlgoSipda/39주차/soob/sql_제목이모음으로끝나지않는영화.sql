SELECT title
FROM film
WHERE rating IN ('NC-17', 'R')
  AND SUBSTR(title, -1) NOT IN ('A', 'E', 'I', 'O', 'U');
