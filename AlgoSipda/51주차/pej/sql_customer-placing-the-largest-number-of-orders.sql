-- [SQL] Customer Placing the Largest Number of Orders
-- https://leetcode.com/problems/customer-placing-the-largest-number-of-orders/description/

select customer_number from Orders 
group by customer_number
having count(customer_number) >= all (
    select count(*) from Orders 
    group by customer_number
);
