-- [SQL] find books with no available copies
-- https://leetcode.com/problems/find-books-with-no-available-copies/


select b.book_id, lb.title, lb.author, lb.genre, lb.publication_year, lb.total_copies as current_borrowers
from borrowing_records b left join library_books lb
on b.book_id = lb.book_id
where b.return_date is null
group by b.book_id
having count(b.borrower_name) = (select l.total_copies from library_books l where l.book_id = b.book_id)
order by lb.total_copies desc, lb.title asc;