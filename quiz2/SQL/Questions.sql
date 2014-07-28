use quiz;

CREATE TABLE IF NOT EXISTS Questions  (
  qid integer(8) primary key,
  qtype integer(4),
  image varchar(256),
  text varchar(256)
);

CREATE TABLE IF NOT EXISTS Answers  (
  qid integer(8),
  aid integer(2),
  primary key (qid,aid),
  image varchar(256),
  text varchar(256)
);

CREATE TABLE IF NOT EXISTS Quizzes  (
  quizid integer(8),
  name   varchar(256),
  instructions varchar(256)
);

CREATE TABLE IF NOT EXISTS QuizzesMatch  (
  quizid integer(8),
  qid integer(8),
  questionOrder integer(4)
);
