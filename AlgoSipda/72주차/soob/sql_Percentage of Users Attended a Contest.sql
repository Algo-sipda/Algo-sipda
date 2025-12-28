SELECT contest_id, ROUND(COUNT(*) / (SELECT COUNT(*) FROM Users), 4) * 100 as percentage
FROM Register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id;