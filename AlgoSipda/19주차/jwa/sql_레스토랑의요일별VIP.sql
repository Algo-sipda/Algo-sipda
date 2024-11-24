SELECT *
FROM tips T1
WHERE total_bill = (
    SELECT MAX(total_bill)
    FROM tips T2
    WHERE T2.day = T1.day
);
