SELECT d.student_id, d.student_name, j.subject_name,
    case when e.subject_name is null then 0
    else count(*)
    end attended_exams
FROM Students d cross join Subjects j left join Examinations e on d.student_id = e.student_id and j.subject_name = e.subject_name
group by d.student_id, j.subject_name
order by d.student_id, j.subject_name;