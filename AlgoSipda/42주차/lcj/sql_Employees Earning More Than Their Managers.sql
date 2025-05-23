# 관리자보다 돈을 더 많이 버는 사람
select name as Employee
from Employee e1
where salary > (select salary
                from Employee e2
                where 1 = 1
                    and e1.managerId = e2.id);