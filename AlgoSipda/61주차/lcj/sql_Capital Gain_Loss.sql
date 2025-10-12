select
    stock_name,
    sum(case 
            when operation = 'Sell' then price
            else -1*price
        end) as capital_gain_loss
from stocks
group by stock_name;