CREATE DATABASE IF NOT EXISTS BookDB;
USE BookDB;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Author;
CREATE TABLE Author
(
  AuthorID VARCHAR(190) NOT NULL,
  Name VARCHAR(190) NOT NULL,
  Age VARCHAR(190) NOT NULL,
  Country VARCHAR(190) NOT NULL,
  PRIMARY KEY(AuthorID)
  -- CONSTRAINT Author_Check CHECK (AuthorID <> '' AND Name <> '')
);
CREATE TABLE Book
(
  ISBN VARCHAR(190),
  Title VARCHAR(190) NOT NULL,
  AuthorID VARCHAR(190) NOT NULL,
  Publisher VARCHAR(190) NOT NULL,
  PublishDate VARCHAR(190) NOT NULL,
  Price VARCHAR(190) NOT NULL,
  PRIMARY KEY(ISBN),
  CONSTRAINT book_author_fk FOREIGN KEY(AuthorID) REFERENCES Author(AuthorID)
  -- CONSTRAINT Book_Check CHECK (ISBN <> '' AND AuthorID <> '' AND Title <>'')
);


INSERT INTO Author (AuthorID, Name, Age, Country)
VALUES ('10011','Marc Leuchner','50','Boston, America'),
  ('10012','俞栋','40','America'),
  ('10013','罗刚','42','中国'),
  ('10014','李俊阳','35','中国'),
  ('10015','《编程之美》小组','2','中国'),
  ('10016','Mehmed Kantardzic','45','America'),
  ('10017','James F.Kurose','50','America'),
  ('10018 ','张晨曦','50','中国'),
  ('10019','哈尔滨工业大学数学系','98','中国'),
  ('10020','张岩','55','中国'),
  ('10021','张岩','60','中国');

INSERT INTO Book (ISBN, Title, AuthorID, Publisher, PublishDate, Price)
VALUES ('978-7-302-20882-2','AIR范例精解','10011','清华大学出版社','2009-09-01','59.80'),
  ('978-7-121-28796-1','语音识别实践','10012','中国工信出版集团','2016-07-01','79.00'),
  ('978-7-121-28620-9','自然语言处理','10013','中国工信出版集团','2016-05-01','79.00'),
  ('978-7-111-51912-6','Xcode','10014','机械工业出版社','2015-11-12','69.00'),
  ('978-7-121-06074-8','编程之美','10015','电子工业出版社','2008-03-23','40.00'),
  ('978-7-302-30714-3','DATA MINING','10016','IEEE PRESS','2013-01-03','59.00'),
  ('978-7-111-45378-9','计算机网络','10017','China Machine Press','2016-11-01','79.00'),
  ('978-7-302-36038-4','计算机系统结构教程（第二版）','10018','清华大学出版社','2014-10-02','39.50'),
  ('978-7-04-037060-7','工科数学分析','10019','高等教育出版社','2008-01-05','33.90'),
  ('978-7-04-037018-8','线性代数与空间解析几何','10019','高等教育出版社','2008-06-12','27.20'),
  ('978-7-5603-3411-0','数理逻辑引论','10020','哈尔滨工业大学出版社','2011-11-11','16.00'),
  ('978-7-04-022473-3','数据结构与算法','10021','Higher Education Press','2000-06-06','29.00');
