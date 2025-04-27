with sub as (
  select genre_id, round(avg(critic_score), 3) sub_critic_score,
    ceil(avg(critic_count)) sub_critic_count,
    round(avg(user_score), 3) sub_user_score,
    ceil(avg(user_count)) sub_user_count
  from games
  where critic_score is not null or critic_count is not null or user_score is not null or user_count is not null
  group by genre_id
)

select game_id, name,
  COALESCE(critic_score, sub_critic_score) critic_score,
  COALESCE(critic_count, sub_critic_count) critic_count,
  COALESCE(user_score, sub_user_score) user_score,
  COALESCE(user_count, sub_user_count) user_count
from games inner join sub on games.genre_id = sub.genre_id
where year >= 2015 and (critic_score is null or user_score is null);