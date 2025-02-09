WITH
  main_platform AS (
    SELECT developer_id,
      platform_id,
      sum(sales_na + sales_eu + sales_jp + sales_other) as sales,
      rank() OVER (PARTITION BY developer_id ORDER BY sum(sales_na + sales_eu + sales_jp + sales_other) DESC) sales_rank
    FROM games
    WHERE developer_id IS NOT NULL
    GROUP BY 1, 2
  )

SELECT c.name AS developer, p.name AS platform, sales
FROM main_platform m
  LEFT JOIN companies c ON m.developer_id = c.company_id
  LEFT JOIN platforms p ON m.platform_id = p.platform_id
WHERE sales_rank = 1;