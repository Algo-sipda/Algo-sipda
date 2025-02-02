SELECT
  a.artist_id,
  a.name
FROM
  artists a
  LEFT JOIN artworks_artists aa ON a.artist_id = aa.artist_id
  LEFT JOIN artworks aw ON aa.artwork_id = aw.artwork_id
WHERE
  aw.artwork_id IS NULL
  AND a.death_year IS NOT NULL;