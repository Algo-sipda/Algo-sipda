-- [SQL]  Game Play Analysis IV https://leetcode.com/problems/game-play-analysis-iv/description/
-- 1) 처음 로그인날 다시 로그인한 플레이어 in 절로 비교 
-- 2. 플레이어 총 수 : distinct로 중복 방지 

select round(count(a1.player_id)/ (select count(distinct(a3.player_id)) from activity a3), 2) as fraction from activity a1
where (a1.player_id, date_sub(a1.event_date, interval 1 day))
in (
    select a2.player_id, min(a2.event_date) from activity a2 group by a2.player_id
);