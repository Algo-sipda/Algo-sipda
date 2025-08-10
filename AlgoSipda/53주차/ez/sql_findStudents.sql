with bTable as (
    select *
    from Scores
    where (student_id, subject, exam_date) in (
        select student_id, subject, min(exam_date)
        from Scores
        group by student_id, subject
    )
),
aTable as (
    select *
    from Scores
    where (student_id, subject, exam_date) in (
        select student_id, subject, max(exam_date)
        from Scores
        group by student_id, subject
    )
)

select b.student_id, b.subject, b.score first_score, a.score latest_score
from bTable b join aTable a on b.student_id = a.student_id and b.subject = a.subject
where b.exam_date != a.exam_date and b.score < a.score
order by student_id, subject;