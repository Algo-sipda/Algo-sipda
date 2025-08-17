-- [SQL] Project Employees I
-- https://leetcode.com/problems/project-employees-i/description/

select p.project_id, round(sum(e.experience_years) / count(e.experience_years),2) as average_years from project p join employee e on p.employee_id = e.employee_id
group by p.project_id
;