select e.name as Employee, e.salary as Salary, d.name as Department from Employee e
join Department d on e.departmentId = d.id
join (
    select departmentId, max(salary) as maxSalary from Employee
    group by departmentId
) as maxSalaries on e.departmentId = maxSalaries.departmentId and e.salary = maxSalaries.maxSalary;

-- select e.name as Employee, e.salary as Salary, d.name as Department from Employee e left join Department d
-- on e.departmentId = d.id
-- where (e.departmentId, e.salary)
-- In (
--     select departmentId, max(salary) from Employee 
--     group by departmentId
-- );

