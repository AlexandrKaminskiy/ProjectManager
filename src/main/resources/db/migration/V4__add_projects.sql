insert into project_info (id,is_deleted,name)
    values (1,false,'new project'),
           (2,false,'special project');
insert into task_info (id,is_deleted,task,project_id)
    values (1,false,'выучить уроки',1),
           (2,false,'помыться',1),
           (3,false,'смонтировать видео',1),
           (4,false,'qqq',2);
insert into usr_project (user_id,project_id)
    values (1,1),(2,2);
insert into project_info_author (project_info_id,author_id)
    values (1,1),(2,2);