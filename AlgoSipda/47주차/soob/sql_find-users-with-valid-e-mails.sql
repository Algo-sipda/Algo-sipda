SELECT *
FROM users
WHERE mail LIKE BINARY '%@leetcode.com'
  AND LEFT(mail, LENGTH(mail) - LENGTH('@leetcode.com'))
      REGEXP '^[a-zA-Z][a-zA-Z0-9._-]*$'