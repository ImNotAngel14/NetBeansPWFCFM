DELIMITER $$

CREATE PROCEDURE selectUser (IN idNum INT)
BEGIN
	SELECT * FROM gatito_blog.users WHERE id = idNum;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE getAllPosts ()
BEGIN
	SELECT 
		posts.postId, posts.title, posts.postText, posts.postImage, posts.isSpoiler,
		users.id AS "userID", users.username, users.profileImage
	FROM posts 
    LEFT JOIN users 
    ON posts.userId = users.id;
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