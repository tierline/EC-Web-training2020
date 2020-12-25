use training;

load data  local infile "./test_data.csv"
into table product
fields terminated by ',' enclosed by'"'
lines terminated by '\r\n'
ignore 1 lines;

load data  local infile "./category.csv"
into table category
fields terminated by ',' enclosed by'"'
lines terminated by '\r\n'
ignore 1 lines;

INSERT IGNORE INTO members(email, password, postcode, prefecture, city, block,  phone_number, lastUpdatedBy, status)
VALUES("test@example.com", "$2a$10$uhuqnvtjTayBhSjs7ezeB.2DG5GlIERAawzRoCROyTxWpzwKy7T.e", 
"012-2211", "北海道", "夕張市", "2-4-3", "012-113-2258", "none", "approval");

-- INSERT INTO members(email, password, address, lastUpdatedBy, status)
-- VALUES("test2@example.com", "$2a$10$EQyVZTFajqzPEHVtmFXW2O7tq.czJbKO/Jf1EMYWziD26CaGzthTi", 
-- "kyoto", "none", "unapproved");

INSERT INTO admins(name, password)
VALUES("admin", "$2a$10$uhuqnvtjTayBhSjs7ezeB.2DG5GlIERAawzRoCROyTxWpzwKy7T.e");
