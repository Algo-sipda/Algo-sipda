-- 게임 배급사로 참여한 게임이 10개 이상인 회사의 이름 출력
SELECT c.name
FROM games g
LEFT JOIN companies c ON g.publisher_id = c.company_id
GROUP BY publisher_id
HAVING count(DISTINCT game_id) >= 10;