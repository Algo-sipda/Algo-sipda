CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  DECLARE result INT;
  DECLARE offset_val INT;

  SET offset_val = N - 1;

  SET result = (
    SELECT salary
    FROM (
      SELECT DISTINCT salary
      FROM Employee
      ORDER BY salary DESC
    ) AS temp
    LIMIT offset_val, 1
  );

  RETURN result;
END