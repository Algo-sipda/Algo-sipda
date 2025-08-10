# 해당 과목의 최근 점수가 첫번째 점수보다 높은 경우 출력
with scoreComp as (
    select
        s1.student_id,
        s1.subject,
        max(case when s1.exam_date = ( select 
                                        min(s2.exam_date) 
                                    from 
                                        scores s2
                                    where 
                                        s2.student_id = s1.student_id 
                                        and s2.subject = s1.subject)
                    then s1.score
            end
        ) as first_score,
        max(case when s1.exam_date = ( select 
                                        max(s2.exam_date) 
                                    from 
                                        scores s2
                                    where 
                                        s2.student_id = s1.student_id 
                                        and s2.subject = s1.subject)
                    then s1.score
            end
        ) as latest_score
    from scores s1
    group by s1.student_id, s1.subject
)
select *
from scoreComp
where first_score < latest_score 
order by student_id, subject;