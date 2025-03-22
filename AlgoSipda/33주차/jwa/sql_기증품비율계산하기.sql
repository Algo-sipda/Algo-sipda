-- 기증품 비율
-- gift 단어 포함

SELECT ROUND(COUNT(CASE WHEN credit LIKE '%gift%' THEN 1 END) * 100.0 / COUNT(*), 3) AS ratio
FROM artworks;
