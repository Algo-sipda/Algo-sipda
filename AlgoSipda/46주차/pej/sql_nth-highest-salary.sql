-- [SQL] Nth Highest Salary
-- https://leetcode.com/problems/nth-highest-salary/description/
-- SQL의 LIMIT은 0-based index를 사용한다. 예를 들어, LIMIT 1 OFFSET 0은 첫번째 행이다 
-- 중복된 급여가 있을 수 있으므로, DISTINCT를 사용하여 고유 급여만 추출한다 
-- 높은 급여 순서대로 정렬한다 

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
SET N = N-1;
  RETURN (
      SELECT DISTINCT(salary) from Employee order by salary DESC
      LIMIT 1 OFFSET N -- N번째 위치까지 건너뛰고, 그 다음 1개를 선택한다 
  );
END