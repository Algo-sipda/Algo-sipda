SELECT YEAR(differentiation_date) AS year, (
    SELECT MAX(size_of_colony) FROM ECOLI_DATA
    WHERE YEAR(differentiation_date) = year) - size_of_colony AS year_dev, id
FROM ECOLI_DATA
ORDER BY year, year_dev