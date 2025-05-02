WITH
  genre_avg AS (
    SELECT
      genre_id,
      ROUND(AVG(critic_score), 3) AS avg_critic_score,
      CEIL(AVG(critic_count)) AS avg_critic_count,
      ROUND(AVG(user_score), 3) AS avg_user_score,
      CEIL(AVG(user_count)) AS avg_user_count
    FROM
      games
    WHERE
      critic_score IS NOT NULL
      OR critic_count IS NOT NULL
      OR user_score IS NOT NULL
      OR user_count IS NOT NULL
    GROUP BY
      genre_id
  )
SELECT
  g.game_id,
  g.name,
  COALESCE(g.critic_score, ga.avg_critic_score) AS critic_score,
  COALESCE(g.critic_count, ga.avg_critic_count) AS critic_count,
  COALESCE(g.user_score, ga.avg_user_score) AS user_score,
  COALESCE(g.user_count, ga.avg_user_count) AS user_count
FROM
  games g
  JOIN genre_avg ga ON g.genre_id = ga.genre_id
WHERE
  g.year >= 2015
  AND (
    g.critic_score IS NULL
    OR g.user_score IS NULL
  );