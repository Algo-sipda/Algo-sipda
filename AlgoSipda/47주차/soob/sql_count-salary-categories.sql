SELECT 
  c.category,
  IFNULL(a.accounts_count, 0) AS accounts_count
FROM (
  SELECT 'Low Salary' AS category
  UNION ALL
  SELECT 'Average Salary'
  UNION ALL
  SELECT 'High Salary'
) AS c
LEFT JOIN (
  SELECT 
    CASE 
      WHEN income < 20000 THEN 'Low Salary'
      WHEN income <= 50000 THEN 'Average Salary'
      ELSE 'High Salary'
    END AS category,
    COUNT(*) AS accounts_count
  FROM Accounts
  GROUP BY category
) AS a ON c.category = a.category;
