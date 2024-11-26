SELECT
    region AS Region,
    COUNT(
            DISTINCT CASE
                         WHEN category = 'Furniture' THEN order_id
                         ELSE NULL
        END
        ) AS 'Furniture',
    COUNT(
            DISTINCT CASE
                         WHEN category = 'Office Supplies' THEN order_id
                         ELSE NULL
        END
        ) AS 'Office Supplies',
    COUNT(
            DISTINCT CASE
                         WHEN category = 'Technology' THEN order_id
                         ELSE NULL
        END
        ) AS 'Technology'
FROM
    records
GROUP BY
    Region
ORDER BY
    Region