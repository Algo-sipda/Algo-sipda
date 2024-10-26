SELECT DATE_FORMAT(sales_date, "%Y-%m-%d") as sales_date, product_id, user_id, sales_amount
FROM (SELECT sales_date, product_id, user_id, sales_amount
      FROM ONLINE_SALE
      UNION
      SELECT sales_date, product_id, NULL as user_id, sales_amount
      FROM OFFLINE_SALE) as SALE
WHERE DATE_FORMAT(sales_date, "%Y-%m") = "2022-03"
ORDER BY sales_date, product_id, user_id;