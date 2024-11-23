SELECT
    MAX(total_bill) AS total_bill,
    tip,
    sex,
    smoker,
    day,
    time,
    size
FROM
    tips
GROUP BY
    day