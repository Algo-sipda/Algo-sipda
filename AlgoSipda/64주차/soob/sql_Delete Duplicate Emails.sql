DELETE p
FROM person p
LEFT JOIN (
  SELECT MIN(id) AS keep_id
  FROM person
  GROUP BY email
) k
ON p.id = k.keep_id
WHERE k.keep_id IS NULL;
