CREATE TABLE movies(
    id INT PRIMARY KEY,
    `name` VARCHAR(40),
    director VARCHAR(40),
    location VARCHAR(40),
    actor VARCHAR(40),
    filmedTime DATE
);

INSERT INTO movies(id, `name`, director, location, actor, filmedTime)
VALUES(1, '唐人街探案3', '陈思诚', '中国大陆', '王宝强', '2021-02-12');

INSERT INTO movies(id, `name`, director, location, actor, filmedTime)
VALUES(2, '罪之声', '土井裕泰', '日本', '小栗旬', '2020-10-30');

CREATE TABLE users(
    id int primary key,
    `username` varchar(40),
    `password` varchar(40),
    `isAdmin` int
);
