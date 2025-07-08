SELECT id, visit_date, people
FROM (
    SELECT s1.*
    FROM Stadium s1
    JOIN Stadium s2 ON s1.id = s2.id + 1
    JOIN Stadium s3 ON s1.id = s3.id - 1
    WHERE s1.people >= 100 AND s2.people >= 100 AND s3.people >= 100

    UNION

    SELECT s.*
    FROM Stadium s
    JOIN Stadium s2 ON s.id = s2.id + 1
    JOIN Stadium s3 ON s.id = s3.id + 2
    WHERE s.people >= 100 AND s2.people >= 100 AND s3.people >= 100

    UNION

    SELECT s.*
    FROM Stadium s
    JOIN Stadium s2 ON s.id = s2.id - 1
    JOIN Stadium s3 ON s.id = s3.id - 2
    WHERE s.people >= 100 AND s2.people >= 100 AND s3.people >= 100
) AS result
ORDER BY visit_date;
