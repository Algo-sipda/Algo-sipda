with grade_table as (
    select EMP_NO, (
        case when AVG(SCORE) >= 96 then "S"
             when AVG(SCORE) >= 90 then "A"
             when AVG(SCORE) >= 80 then "B"
             else "C"
            end
        ) GRADE
    from HR_GRADE
    group by EMP_NO
)

select e.EMP_NO, e.EMP_NAME, g.GRADE, (
    case when g.GRADE like "S" then e.SAL * 0.2
         when g.GRADE like "A" then e.SAL * 0.15
         when g.GRADE like "B" then e.SAL * 0.1
         else 0
        end
    ) BONUS
from grade_table g left join hr_employees e on g.EMP_NO = e.EMP_NO
order by e.EMP_NO;