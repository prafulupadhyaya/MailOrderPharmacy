insert into drug values(1,1000.0,'2020-08-10 01:17:40.051','2020-08-10 01:17:40.051','drugmaker','pcm',100);
insert into drug values(4,500.0,'2020-08-10 01:17:40.051','2020-08-10 01:17:40.051','drugmaker2','crocine',1000);
insert into drug_details (location, quantity, id) values ('dehradun',100,2);
insert into drug_details (location, quantity, id) values ('delhi',100,3);
insert into drug_details (location, quantity, id) values ('dehradun',500,5);
insert into drug_details (location, quantity, id) values ('delhi',5000,6);
insert into drug_drug_details (drugs_id, drug_details_id) values (1, 2);
insert into drug_drug_details (drugs_id, drug_details_id) values (1, 3);
insert into drug_drug_details (drugs_id, drug_details_id) values (4, 5);
insert into drug_drug_details (drugs_id, drug_details_id) values (4, 6);