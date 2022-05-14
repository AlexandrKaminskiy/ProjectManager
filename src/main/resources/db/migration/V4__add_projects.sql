insert into project_info (id,is_deleted,name,company_name,is_ready)
    values (1,false,'new project','StoneSoft',false),
           (2,false,'special project','StoneSoft',true),
           (3,false,'orrin project','KamenSoft',false),
           (4,false,'valeska project','KamenSoft',false),
           (5,false,'christian project','StoneSoft',true),
           (6,false,'anabel project','StoneSoft',true),
           (7,false,'djeremi project','KamenSoft',false),
           (8,false,'cassiopeya project','StoneSoft',true),
           (9,false,'ivor project','StoneSoft',false);
insert into task_info (id,is_deleted,task,project_id)
    values (1,false,'выучить уроки',1),
           (2,false,'помыться',1),
           (3,false,'смонтировать видео',1),
           (4,false,'qqq',2);
insert into usr_project (user_id,project_id)
    values (1,1),
           (2,2),
           (3,3),
           (1,4),
           (2,5),
           (3,6),
           (1,7),
           (2,8),
           (3,9);
insert into project_info_author (project_info_id,author_id)
    values  (1,1),
            (2,2),
            (3,3),
            (4,1),
            (5,2),
            (6,3),
            (7,1),
            (8,2),
            (9,3);
