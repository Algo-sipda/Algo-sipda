SELECT e.name, b.bonus as bonus
FROM employee e
LEFT JOIN bonus b
ON e.empId = b.empID
WHERE b.bonus < 1000 or b.bonus is null
