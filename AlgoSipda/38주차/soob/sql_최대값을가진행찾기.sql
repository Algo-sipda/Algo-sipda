SELECT
    id
FROM (
    SELECT
        id
    FROM
        points
    WHERE
        x = (SELECT MAX(x) FROM points)

    UNION

    SELECT
        id
    FROM
        points
    WHERE
        y = (SELECT MAX(y) FROM points)
) sub
ORDER BY
    id ASC;