(
  SELECT u.name AS results
  FROM Users u
  JOIN MovieRating r ON r.user_id = u.user_id
  GROUP BY u.user_id
  ORDER BY COUNT(*) DESC, u.name
  LIMIT 1
)
UNION ALL
(
  SELECT m.title AS results
  FROM Movies m
  JOIN MovieRating r ON r.movie_id = m.movie_id
  WHERE r.created_at >= '2020-02-01' AND r.created_at < '2020-03-01'
  GROUP BY m.movie_id
  ORDER BY AVG(r.rating) DESC, m.title
  LIMIT 1
);
