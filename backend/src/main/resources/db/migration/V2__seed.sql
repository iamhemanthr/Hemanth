INSERT INTO talent (name,email,phone,primary_skill,years_exp,location,current_status,created_at,updated_at)
VALUES
 ('Alice','alice@example.com','9000000001','Java',5,'BLR','AVAILABLE',now(),now()),
 ('Bob','bob@example.com','9000000002','React',3,'HYD','INTERVIEWING',now(),now()),
 ('Carol','carol@example.com','9000000003','DevOps',7,'BLR','DEPLOYED',now(),now());

INSERT INTO talent_secondary_skills (talent_id, secondary_skills) VALUES
 (1,'Spring'),(1,'Docker'),(2,'TypeScript'),(3,'Kubernetes');

INSERT INTO interview (talent_id, round_name, interviewer, mode, scheduled_at, result, feedback, rating, recorded_at)
VALUES (1,'HR','Ravi','Online',now(),'PENDING',null,null,now());

INSERT INTO deployment (talent_id, client, project, role_title, start_date, end_date, deployment_status, remarks)
VALUES (3,'Acme Corp','Payments','Senior Dev','2024-01-01',null,'DEPLOYED','Ongoing');

INSERT INTO status_history (talent_id, old_status, new_status, changed_by, reason, changed_at)
VALUES (2,'AVAILABLE','INTERVIEWING','seed','Scheduled interview', now());
