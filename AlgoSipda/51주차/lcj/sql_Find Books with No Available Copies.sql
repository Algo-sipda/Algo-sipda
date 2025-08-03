# 1. 현재 대출 중인 도서 중 보관되어 있는 책이 없는 것
# 2. null이 있는 대출 기록은 대출된 걸로 간주
select
    lb.book_id,
    lb.title,
    lb.author,
    lb.genre,
    lb.publication_year,
    lb.total_copies as current_borrowers
from 
    library_books lb
join 
    borrowing_records br
    on lb.book_id = br.book_id
where 
    1 = 1 
    and br.return_date is null
group by
    lb.book_id
having
    count(*) = (
                SELECT total_copies 
                FROM library_books l 
                WHERE l.book_id = lb.book_id
                )
order by
    current_borrowers desc,
    title;