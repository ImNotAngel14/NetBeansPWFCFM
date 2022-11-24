DELIMITER $$

CREATE PROCEDURE selectUser (IN idNum INT)
BEGIN
	SELECT * FROM gatito_blog.users WHERE id = idNum;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE gatito_blog.getActivePosts ()
BEGIN
	SELECT 
		posts.postId, posts.postText, posts.postImage, posts.isSpoiler, posts.uploadDate,
		users.id AS "userID", users.username
	FROM gatito_blog.posts 
    LEFT JOIN gatito_blog.users 
    ON posts.userId = users.id WHERE posts.deleted = false;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE getUserPosts (IN idNum INT)
BEGIN
	SELECT 
		posts.postId, posts.uploadDate, posts.title, posts.postText, posts.postImage, posts.isSpoiler,
		users.id AS "userID", users.username, users.profileImage
	FROM posts 
    LEFT JOIN users 
    ON posts.userId = users.id
    WHERE posts.userId = idNum;
END$$

DELIMITER ;