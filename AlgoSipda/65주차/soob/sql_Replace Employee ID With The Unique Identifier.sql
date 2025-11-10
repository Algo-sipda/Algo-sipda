SELECT unique_id, name
FROM Employees e1
LEFT JOIN EmployeeUNI e2
on e1.id = e2.id;