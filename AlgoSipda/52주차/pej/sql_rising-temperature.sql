-- https://leetcode.com/problems/rising-temperature/solutions/3716884/mysql-simple-and-clean-beats-88/
-- 전날보다 온도가 높은 날의 id를 구하는 문제
-- w1은 현재 날, w2는 전날을 나타내는 테이블로 조인한다.
-- datediff를 사용하여 두 날짜의 차이가 1인 경우를 찾는다.
-- w1의 온도가 w2보다 높은 경우를 필터링하여 결과를 반환한다.
select w1.id
from weather w1, weather w2
where datediff(w1.recordDate, w2.recordDate) = 1
and w1.temperature > w2.temperature;