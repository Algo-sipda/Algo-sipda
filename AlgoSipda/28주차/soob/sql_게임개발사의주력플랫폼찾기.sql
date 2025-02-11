WITH platform_sales AS (
    SELECT developer_id, platform_id, SUM(sales_na + sales_eu + sales_jp + sales_other) AS total_sales
    FROM games
    WHERE developer_id IS NOT NULL
    GROUP BY developer_id, platform_id
),
ranked_platforms AS (
    SELECT developer_id, platform_id, total_sales, 
           RANK() OVER (PARTITION BY developer_id ORDER BY total_sales DESC) AS sales_rank
    FROM platform_sales
)
SELECT c.name AS developer, p.name AS platform, r.total_sales AS sales
FROM ranked_platforms r
LEFT JOIN companies c ON r.developer_id = c.company_id
LEFT JOIN platforms p ON r.platform_id = p.platform_id
WHERE r.sales_rank = 1;
