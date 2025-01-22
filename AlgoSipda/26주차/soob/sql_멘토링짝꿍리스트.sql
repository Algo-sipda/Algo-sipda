WITH mentees AS (
    SELECT 
        employee_id AS mentee_id,
        name AS mentee_name,
        department AS mentee_department
    FROM employees
    WHERE join_date BETWEEN DATE('2021-10-01') AND DATE('2021-12-31')
),
mentors AS (
    SELECT 
        employee_id AS mentor_id,
        name AS mentor_name,
        department AS mentor_department
    FROM employees
    WHERE join_date <= DATE('2019-12-31')
)
SELECT 
    mentees.mentee_id,
    mentees.mentee_name,
    mentors.mentor_id,
    mentors.mentor_name
FROM mentees
LEFT JOIN mentors
    ON mentees.mentee_department != mentors.mentor_department
ORDER BY mentees.mentee_id, mentors.mentor_id;
