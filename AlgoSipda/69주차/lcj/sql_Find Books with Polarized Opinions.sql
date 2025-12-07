# Write your MySQL query statement below
with book_stats as(
    select book_id,
        count(*) total_session,
        min(session_rating) minrating,
        max(session_rating) maxrating,
        sum(
            case
                when session_rating > 3 then 1
                else 0
            end
        ) highrating,
        sum(
            case
                when session_rating < 3 then 1
                else 0
            end
        ) lowrating,
        sum(
            case
                when session_rating > 3
                or session_rating < 3 then 1
                else 0
            end
        ) extremes
    from reading_sessions
    group by 1
)
select book_id,
    title,
    author,
    genre,
    pages,
    maxrating - minrating rating_spread,
    round(extremes * 1.0 / total_session, 2) polarization_score
from book_stats
    join books using(book_id)
where total_session > 4
    and highrating > 0
    and lowrating > 0
having polarization_score >= 0.6
order by polarization_score desc,
    title desc;