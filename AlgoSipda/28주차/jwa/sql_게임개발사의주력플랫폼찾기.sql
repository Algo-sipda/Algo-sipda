-- sales 컬럼 다 더하기
-- 플랫폼별 총 판매량 구하고 순위 매기기

WITH sales_total AS (
  SELECT developer_id, platform_id, sales_eu + sales_jp + sales_na + sales_other AS sales
  FROM games
),
ranked AS (
  SELECT developer_id, platform_id, sum(sales) AS sales, 
    RANK() OVER (PARTITION BY developer_id ORDER BY sum(sales) DESC) AS sales_rank
  FROM sales_total
  GROUP BY developer_id, platform_id
)

SELECT DISTINCT c.name AS developer, p.name AS platform, sales
FROM ranked r
INNER JOIN companies c ON company_id = developer_id
INNER JOIN platforms p USING(platform_id)
WHERE sales_rank = 1;
