SELECT 
    ROUND(
        100.0 * SUM(CASE WHEN credit LIKE '%gift%' THEN 1 ELSE 0 END) / COUNT(*), 
        3
    ) AS ratio
FROM artworks;
