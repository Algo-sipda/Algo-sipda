with edTable as (select d.name as Department, e.name as Employee, e.salary as Salary, dense_rank () over (partition by d.name order by e.salary desc) as rankNum
from employee e
join department d
on e.departmentId = d.id)
select department, employee, salary
from edTable
where rankNum <= 3;