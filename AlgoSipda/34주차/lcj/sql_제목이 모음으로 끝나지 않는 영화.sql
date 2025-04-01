-- 17세 미만 학생이 DVD 대여점에 혼자와서는 대여할 수 없는 영화 중 제목이 모음 'A, E, I, O, U'로 끝나지 않는 영화를 찾고 싶다.
-- 조건에 맞는 영화 제목(title)을 출력하는 쿼리 작성(다른 컬럼 출력X)
select
  title
from
  film
where
  1 = 1
  and rating in ('NC-17', 'R')
  and substring(title, -1, 1) not in('A', 'E', 'I', 'O', 'U');