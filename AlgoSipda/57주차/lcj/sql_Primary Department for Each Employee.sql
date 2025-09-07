select
    employee_id, department_id
from
    employee
group by
    employee_id
having
    count(*) = 1

union

select
    employee_id, department_id
from
    employee
where
    primary_flag = 'Y';