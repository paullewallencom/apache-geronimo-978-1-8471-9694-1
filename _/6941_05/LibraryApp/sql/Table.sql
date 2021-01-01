CREATE TABLE BOOKS(Title char(50),ISBN char(20),lent_date date,due_date date,PRIMARY KEY (ISBN));


CREATE TABLE MEMBERS(Id char(4),Name char(50),Address char(150),email char(50),books_isbn char(20) references BOOKS(ISBN),PRIMARY KEY (Id));

INSERT INTO BOOKS VALUES ('To Kill A MockingBird', 'ISBN9780899668581', '10/10/2008', '10/11/2008');
INSERT INTO BOOKS VALUES ('Let Sleeping Vets Lie', 'ISBN9780330443548', '04/04/2009', '05/05/2009');
INSERT INTO BOOKS VALUES ('Summer Lightning', 'ISBN9997520556', '03/03/2009', '04/04/2010');
INSERT INTO BOOKS VALUES ('Five Little Pigs', 'ISBN0007274564', '01/01/2009', '01/08/2010');


INSERT INTO MEMBERS VALUES ('0001', 'Tom', 'Majestic', 'tom@gmail.com', 'ISBN9780330443548');
INSERT INTO MEMBERS VALUES ('0002', 'Dick', 'Millers Road', 'dick@yahoo.com', 'ISBN9997520556');
INSERT INTO MEMBERS VALUES ('0003', 'Harry', 'CunningHam Road', 'harry@aol.com', 'ISBN9780899668581');
INSERT INTO MEMBERS VALUES ('0004', 'Sally', 'Dickenson Road', 'sally@msn.com', 'ISBN0007274564');




