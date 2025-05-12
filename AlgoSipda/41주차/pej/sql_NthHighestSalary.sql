-- [SQL] Nth Highest Salary https://leetcode.com/problems/nth-highest-salary/description/

`
CREATE FUNCTION : 저장 함수를 생성하는 구문  
RETURNS 키워드를 통해 반환 타입을 지정 

BEGIN ... END : 함수의 본문을 감싸는 블록 

SET N = N-1;
` 
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
SET N = N-1;
  RETURN (
      SELECT distinct(salary) FROM Employee order by salary DESC
      LIMIT 1 OFFSET N
  );
END