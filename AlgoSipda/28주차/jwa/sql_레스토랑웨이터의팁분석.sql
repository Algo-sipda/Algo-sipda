-- 요일, 시간대별 패턴
-- 시간대별 평균 팁, 평균 일행수
-- 셋째자리 반올림
-- 요일, 시간대 오름차

SELECT day, time, ROUND(AVG(tip), 2) AS avg_tip, ROUND(AVG(size), 2) AS avg_size
FROM tips
GROUP BY day, time
ORDER BY day, time;