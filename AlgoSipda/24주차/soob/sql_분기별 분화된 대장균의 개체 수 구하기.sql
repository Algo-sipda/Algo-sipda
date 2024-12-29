SELECT 
    CASE
        WHEN MONTH(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
        WHEN MONTH(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
        WHEN MONTH(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
        WHEN MONTH(DIFFERENTIATION_DATE) <= 12 THEN '4Q'
    END AS QUARTER, 
    COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER
ORDER BY QUARTER;