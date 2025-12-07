WITH stats AS (
    SELECT
        customer_id,
        SUM(transaction_type = 'purchase') AS purchase_cnt,
        SUM(transaction_type = 'refund')   AS refund_cnt,
        DATEDIFF(MAX(transaction_date), MIN(transaction_date)) AS active_days
    FROM customer_transactions
    GROUP BY customer_id
)
SELECT
    customer_id
FROM
    stats
WHERE
    purchase_cnt >= 3
    AND active_days >= 30
    AND refund_cnt / (purchase_cnt + refund_cnt) < 0.2
ORDER BY
    customer_id;
