SELECT IFNULL((
    SELECT num
    FROM (
        SELECT num, COUNT(*) as cnt
        FROM mynumbers
        GROUP BY num
    ) as a
    WHERE cnt = 1
    ORDER BY num DESC
    LIMIT 1
), null) as num;
