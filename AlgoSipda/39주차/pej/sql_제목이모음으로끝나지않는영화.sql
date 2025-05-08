-- [SQL] 제목이 모음으로 끝나지 않는 영화 https://solvesql.com/problems/film-ending-with-consonant/
-- 17세 미만 학생이 DVD 대여점에 혼자와서는 대여할 수 없는 영화 
-- 정규 표현식 : [AEIOU]$ => 끝($)에 [AEIOU] 가 오는 경우 

SELECT title 
FROM FILM
WHERE rating IN ('R', 'NC-17') AND title NOT REGEXP '[AEIOU]$';

-- SELECT title FROM FILM
-- WHERE rating IN ("R", "NC-17")
-- AND title NOT LIKE '%A' 
-- AND title NOT LIKE '%E' 
-- AND title NOT LIKE '%I' 
-- AND title NOT LIKE '%O'
-- AND title NOT LIKE '%U'; 