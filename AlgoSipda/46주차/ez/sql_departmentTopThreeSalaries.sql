select Department, Employee, Salary
from (
    select d.name Department, e.name Employee, salary Salary, dense_rank() over (partition by d.id order by salary desc) total_rank
    from Employee e left join Department d on e.departmentId = d.id) rank_table
where total_rank <= 3;