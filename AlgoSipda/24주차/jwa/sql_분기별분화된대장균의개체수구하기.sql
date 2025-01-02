-- 각 분기별 분화된 대장균 개체 총 수
-- 분화 날짜에서 분기를 구함
-- 분기로 그룹화, 카운트
-- 분기 오름차순

SELECT CONCAT(
        QUARTER(differentiation_date), 'Q'
    ) AS QUARTER, COUNT(*) AS ECOLI_COUNT
FROM ecoli_data
GROUP BY
    CONCAT(
        QUARTER(differentiation_date),
        'Q'
    )
ORDER BY QUARTER;