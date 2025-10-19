select stock_name, sum(price) capital_gain_loss
from (
    SELECT stock_name, operation, operation_day, sum(price) * -1 "price"
    FROM Stocks
    where operation = 'Buy'
    group by stock_name
    union all
    select stock_name, operation, operation_day, sum(price) * 1 "price"
    from stocks
    where operation = 'Sell'
    group by stock_name
) s
group by stock_name;

select stock_name,
    sum(
        case when operation = 'Sell' then price
        else -price
        end
    ) as capital_gain_loss
from Stocks
group by stock_name