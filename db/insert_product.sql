use training;

-- load data  local infile "./test_data.csv"
-- into table PRODUCT
-- fields terminated by ',' enclosed by'"'
-- lines terminated by '\r\n'
-- ignore 1 lines;


-- load data  local infile "./category.csv"
-- into table CATEGORY
-- fields terminated by ',' enclosed by'"'
-- lines terminated by '\r\n'
-- ignore 1 lines;

insert into MEMBERS(email, password, address)
values("abc@example.com", "$2y$12$Y/SeZIzTiRji9ffrRLduFORb3p/JOzs87FfMgIaCVSBEy1XtprNLi", "hyogo");

insert into MEMBERS(email, password, address)
values("xyz@example.com", "$2a$10$DOmanvP.s9Y5ygfhykdB7.BPT3efd3EB9AXL2h5pAF7CLLjYYbQj.", "kyoto");

insert into ADMIN(name, password)
values("admin", "$2y$12$Y/SeZIzTiRji9ffrRLduFORb3p/JOzs87FfMgIaCVSBEy1XtprNLi");
