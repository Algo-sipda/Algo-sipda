-- 2015년 이후에 발매한 게임 중
-- 누락된 평점 정보가 있는 게임에 대해서 같은 장르를 가진 전체 게임의 평균 평점과 평균 평론가/사용자수를 사용해 누락된 정보 채우기
WITH
  cri AS (
    SELECT
      genre_id,
      round(avg(critic_score), 3) as critic_score_avg,
      ceil(avg(critic_count)) as critic_count_avg,
      round(avg(user_score), 3) as user_score_avg,
      ceil(avg(user_count)) as user_count_avg
    from
      games
    WHERE
      1 = 1
      and critic_score is not null
      or critic_count is not null
      or user_score is not null
      or user_count is not null
    group by
      genre_id
  )
SELECT
  game_id,
  name,
  coalesce(critic_score, critic_score_avg) as critic_score,
  coalesce(critic_count, critic_count_avg) as critic_count,
  coalesce(user_score, user_score_avg) as user_score,
  coalesce(user_count, user_count_avg) as user_count
from
  games
  inner join cri on games.genre_id = cri.genre_id
where
  1 = 1
  and year >= 2015
  and (
    critic_score is null
    or user_score is null
  );