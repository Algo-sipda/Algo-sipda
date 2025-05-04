WITH sub AS (
    SELECT
        genre_id,
        ROUND(AVG(critic_score), 3) AS sub_critic_score,
        CEIL(AVG(critic_count)) AS sub_critic_count,
        ROUND(AVG(user_score), 3) AS sub_user_score,
        CEIL(AVG(user_count)) AS sub_user_count
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
    COALESCE(g.critic_score, s.sub_critic_score) AS critic_score,
    COALESCE(g.critic_count, s.sub_critic_count) AS critic_count,
    COALESCE(g.user_score, s.sub_user_score) AS user_score,
    COALESCE(g.user_count, s.sub_user_count) AS user_count
FROM
    games g
INNER JOIN
    sub s
    ON g.genre_id = s.genre_id
WHERE
    g.year >= 2015
    AND (g.critic_score IS NULL OR g.user_score IS NULL);