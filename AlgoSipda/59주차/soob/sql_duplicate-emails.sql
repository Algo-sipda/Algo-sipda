SELECT email
FROM (
    SELECT email, COUNT(*) as cnt
    FROM person
    GROUP BY email
) a
WHERE cnt >= 2
