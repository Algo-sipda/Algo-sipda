-- 편차: 연도별 가장 큰 대장균의 크기 - 각 대장균의 크기
-- 연도 오름차순, 같은 연도는 편차 오름차순

SELECT 
    YEAR(DIFFERENTIATION_DATE) AS YEAR,
    (
        (SELECT MAX(SIZE_OF_COLONY)
         FROM ECOLI_DATA
         WHERE YEAR(DIFFERENTIATION_DATE) = YEAR)
        - SIZE_OF_COLONY
    ) AS YEAR_DEV,
    ID
FROM ECOLI_DATA
ORDER BY YEAR, YEAR_DEV;
