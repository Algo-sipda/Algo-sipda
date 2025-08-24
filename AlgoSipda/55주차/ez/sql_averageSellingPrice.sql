select p.product_id, ifnull(round(sum(u.units * p.price) / sum(units), 2), 0) average_price
from Prices p left join UnitsSold u
on p.product_id = u.product_id
and u.purchase_date between p.start_date and p.end_date
group by p.product_id