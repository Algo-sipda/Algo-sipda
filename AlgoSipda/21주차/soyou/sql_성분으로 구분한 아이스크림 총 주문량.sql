SELECT ingredient_type, SUM(total_order) AS total_order
FROM first_half as f, icecream_info as i
WHERE f.FLAVOR = i.FLAVOR
GROUP BY ingredient_type
ORDER BY total_order;