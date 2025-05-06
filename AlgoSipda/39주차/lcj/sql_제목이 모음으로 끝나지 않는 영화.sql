-- 17세 미만 학생이 dvd 대여점에 혼자와서 대여할 수 없는 영화 제목
-- 중에서도 모음으로 끝나지 않는 영화
select
  title
from
  film
where
  1 = 1
  and rating in ('R', 'NC-17')
  and substr(title, -1) not in('A', 'E', 'I', 'O', 'U');