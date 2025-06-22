-- [SQL] https://leetcode.com/problems/department-top-three-salaries/

select d.name as Department, e.Employee as Employee, e.Salary as Salary
from (
    select 
        name as Employee, 
        salary as Salary, 
        departmentId,
        DENSE_RANK() over (partition by departmentId order by salary desc) as r
    from Employee
) as e
 left join Department d on e.departmentId = d.id
 WHERE e.r <= 3;
