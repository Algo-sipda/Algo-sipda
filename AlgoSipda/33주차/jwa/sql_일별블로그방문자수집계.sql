-- 기간동안 날짜별로 방문자 수
-- 아이디 중복제거

SELECT event_date_kst AS dt, COUNT(user_pseudo_id) AS users
FROM (
  SELECT DISTINCT event_date_kst, user_pseudo_id
  FROM ga
)
WHERE event_date_kst BETWEEN '2021-08-02' AND '2021-08-09'
GROUP BY event_date_kst
ORDER BY event_date_kst;
