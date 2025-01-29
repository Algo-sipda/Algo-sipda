SELECT artist_id, name
FROM artists
WHERE artist_id NOT IN (SELECT artist_id FROM artworks_artists)
AND death_year IS NOT NULL;