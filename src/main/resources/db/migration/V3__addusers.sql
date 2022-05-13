insert into usr (id, username, password, active)
    values (2,'Alexandr','$2a$10$RQBJTw/sSkop.6nmHR5/Z.PX3EbVbdN5Xiwbau0sOfR/sBvzqyMvi',true),
           (3,'Alexey','$2a$10$RQBJTw/sSkop.6nmHR5/Z.PX3EbVbdN5Xiwbau0sOfR/sBvzqyMvi',true);
insert into user_role (user_id,roles)
    values (2,'USER'),
           (3,'USER');