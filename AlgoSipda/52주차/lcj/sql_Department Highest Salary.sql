# 각 부서에서 최고로 높은 연봉
select 
        d.name Department, 
        e.name Employee, 
        e.salary
from 
    employee e
join 
    department d
on 
    e.departmentId = d.id
where 
    (e.departmentId, e.salary) in (select departmentId, max(salary) from employee group by departmentId);