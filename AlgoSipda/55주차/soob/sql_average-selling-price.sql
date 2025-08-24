select
  p.product_id,
  round(ifnull(sum(p.price * u.units) / sum(u.units), 0), 2) as average_price
from prices p
left join unitssold u
  on u.product_id = p.product_id
 and u.purchase_date between p.start_date and p.end_date
group by p.product_id;