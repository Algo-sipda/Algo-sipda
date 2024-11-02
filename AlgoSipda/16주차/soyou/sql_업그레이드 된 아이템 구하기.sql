SELECT item_id, item_name, rarity
FROM item_info
WHERE item_id IN (SELECT tree.item_id
                  FROM (SELECT item_id
                        FROM item_info
                        WHERE rarity = "RARE") as info
                           JOIN item_tree as tree
                                ON info.item_id = tree.parent_item_id)
ORDER BY item_id desc;