SELECT mem.member_name, r.review_text, DATE_FORMAT(r.review_date, "%Y-%m-%d") as review_date
FROM rest_review as r
         JOIN member_profile as mem
              ON r.member_id = mem.member_id
WHERE r.member_id in (
    SELECT member_id
    FROM (
             SELECT member_id, count(review_id) as count, rank() over(ORDER BY count(review_id) desc) as rnk
             FROM rest_review
             GROUP BY member_id
         ) as most
    WHERE rnk = 1)
ORDER BY review_date, review_text;