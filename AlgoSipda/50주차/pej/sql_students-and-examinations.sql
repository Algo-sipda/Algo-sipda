-- [SQL] Students and Examinations
-- https://leetcode.com/problems/students-and-examinations/description/
select st.student_id, st.student_name, sub.subject_name , count(ex.subject_name) as attended_exams
from Students as st 
cross join Subjects as sub 
left join Examinations ex 
on st.student_id = ex.student_id and sub.subject_name = ex.subject_name
group by st.student_id, sub.subject_name
order by st.student_id,sub.subject_name
; 