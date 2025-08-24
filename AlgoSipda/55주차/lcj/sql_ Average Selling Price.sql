# find the average selling price for each product
# average_price should be rounded to 2 decimal places
# 판매된 제품 X -> 평균 판매가 : 0
select 
    p.product_id, 
    ifnull(round(sum(price*units)/sum(units), 2), 0) as average_price
from 
    prices p
left join 
    unitssold u
on 
    p.product_id = u.product_id
    and purchase_date between start_Date and end_date
group by
    p.product_id;