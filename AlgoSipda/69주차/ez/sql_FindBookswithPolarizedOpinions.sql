SELECT books.book_id, title, author, genre, pages, rating_spread, polarization_score
FROM books JOIN (
    SELECT book_id,
        MAX(session_rating) - MIN(session_rating) rating_spread,
        ROUND(SUM(session_rating != 3) / COUNT(*), 2) polarization_score
    FROM reading_sessions
    GROUP BY book_id
    HAVING COUNT(*) >= 5 AND MIN(session_rating) <= 2 AND MAX(session_rating) >= 4
) R ON books.book_id = R.book_id
WHERE polarization_score >= 0.6
ORDER BY polarization_score DESC, title DESC;