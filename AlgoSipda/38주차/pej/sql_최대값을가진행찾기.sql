-- [SQL] 최대값을 가진 행 찾기 https://solvesql.com/problems/max-row/

SELECT p.id
FROM points p
CROSS JOIN (
    SELECT MAX(x) AS max_x, MAX(y) AS max_y FROM points
) AS max_vals
WHERE p.x = max_vals.max_x
   OR p.y = max_vals.max_y
ORDER BY p.id;
