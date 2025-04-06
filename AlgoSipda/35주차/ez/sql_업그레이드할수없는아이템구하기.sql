select item_id, item_name, rarity
from item_info i left join
 (
    select distinct(parent_item_id)
    from item_tree
) t on i.item_id = t.parent_item_id
where parent_item_id is null
order by item_id desc
;
