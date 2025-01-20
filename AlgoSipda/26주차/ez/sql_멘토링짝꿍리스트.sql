-- 직원ID | 직원의 이름 | 입사일 | 부서 | 직속 상사ID
-- 멘티: 2021년 12월 31일 기준 3개월 이내 입사한 인원 전체
-- 멘토: 2021년 12월 31일 기준으로 재직한지 2년 이상
-- 서로 다른 부서에 속하는 직원끼리
-- 매칭 가능한 멘토 없는 경우 포함
-- 멘티id 기준, 멘토 id 기준

WITH
  mentee AS (
    SELECT
      employee_id AS mentee_id,
      name AS mentee_name,
      department
    FROM
      employees
    WHERE
      join_date BETWEEN '2021-09-30' AND '2021-12-31'
  ),
  mentor AS (
    SELECT
      employee_id AS mentor_id,
      name AS mentor_name,
      department
    FROM
      employees
    WHERE
      join_date < '2019-12-31'
  )
select
  mentee_id,
  mentee_name,
  mentor_id,
  mentor_name
from
  mentee
  cross join mentor
where
  mentee.department != mentor.department
order by
  mentee_id,
  mentor_id;