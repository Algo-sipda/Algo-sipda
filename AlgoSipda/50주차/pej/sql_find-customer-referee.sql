-- [SQL] Find Customer Referee
-- https://leetcode.com/problems/find-customer-referee/description/
select c.name from Customer c  where c.referee_id != 2 or c.referee_id is null;
