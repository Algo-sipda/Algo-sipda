# Write your MySQL query statement below
SELECT name, balance
FROM Users u LEFT JOIN (
    SELECT account, SUM(amount) balance
    FROM Transactions
    GROUP BY account
) t ON u.account = t.account
WHERE balance > 10000;
