use quiz;

CREATE TABLE Questions IF NOT EXISTS (
  qid integer(8) primary key,
  qtype integer(4),
  image varchar(256).
  text varchar(256)
);

CREATE TABLE Answers IF NOT EXISTS (
  qid integer(8),
  aid integer(2),
  primary key (qid,aid),
  image varchar(256),
  text varchar(256)
);

CREATE TABLE Quizzes IF NOT EXISTS (
  quizid integer(8),
  name   varchar(256),
  instructions varchar(256)
);

CREATE TABLE QuizzesMatch IF NOT EXISTS (
  quizid integer(8),
  qid integer(8),
  questionOrder integer(4)
);
