SELECT id,
    CASE
        WHEN p_id IS NULL THEN "Root"
        WHEN id IN (
                SELECT DISTINCT p_id
                FROM Tree
                GROUP BY p_id) THEN "Inner"
        ELSE "Leaf"
        END type
FROM Tree;