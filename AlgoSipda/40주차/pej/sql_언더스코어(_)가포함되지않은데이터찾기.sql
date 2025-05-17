-- [SQL]  언더스코어(_)가 포함되지 않은 데이터 찾기 https://solvesql.com/problems/data-without-underscore/
-- LIKE 구문에서 사용되는 _ 와 % 는 와일드카드 문자로 
-- 그대로 문자열로 사용하고 싶을 때 1) \와 ESCAPE 를 사용해야 한다 
SELECT DISTINCT(page_location) FROM GA WHERE page_location NOT LIKE '%\_%' ESCAPE '\' ORDER BY page_location;