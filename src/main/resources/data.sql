INSERT INTO `users` (`id`, `enabled`, `password`, `username`, `email`) VALUES (NULL, '1', 'admin', 'admin', 'admin@admin.pl');
INSERT INTO `role` (`role_id`, `role`) VALUES (1, 'ROLE_MODERATOR');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');

INSERT INTO `tags` (`id`, `active`, `name`) VALUES (1, '1', 't11'), (2, '1', 't22');

INSERT INTO `posts` (`id`, `content`, `created`, `description`, `last_updated_at`, `title`, `user_id`) VALUES (1, 'post 1 content', '2018-02-03 10:46:07', 'post 1 description', '2018-02-03 10:46:07', 'post 1 title', 1);

INSERT INTO `posts` (`id`, `content`, `created`, `description`, `last_updated_at`, `title`, `user_id`) VALUES (2, 'post 2 content', '2018-02-03 10:46:07', 'post 2 description', '2018-02-03 10:46:07', 'post 2 title', 1);
INSERT INTO `posts` (`id`, `content`, `created`, `description`, `last_updated_at`, `title`, `user_id`) VALUES (3, 'post 3 content', '2018-02-03 10:46:07', 'post 3 description', '2018-02-03 10:46:07', 'post 3 title', 1);
INSERT INTO `post_tags` (`post_id`, `tag_id`) VALUES (1, 1);

INSERT INTO `student` (`id`, `email`, `first_name`, `last_name`) VALUES (1, 'student1@student.pl', 'student1FirstName', 'student1LastName');
INSERT INTO `student` (`id`, `email`, `first_name`, `last_name`) VALUES (2, 'student2@student.pl', 'student2FirstName', 'student2LastName');
INSERT INTO `student` (`id`, `email`, `first_name`, `last_name`) VALUES (3, 'student3@student.pl', 'student3FirstName', 'student3LastName');
INSERT INTO `student` (`id`, `email`, `first_name`, `last_name`) VALUES (4, 'student4@student.pl', 'student4FirstName', 'student4LastName');
