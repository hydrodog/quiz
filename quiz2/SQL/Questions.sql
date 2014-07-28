use quiz;

CREATE TABLE IF NOT EXISTS Questions  (
  qid integer(8) primary key,
  qtype integer(4),
  title varchar(256),
  image varchar(256),
  text varchar(256)
);

CREATE TABLE IF NOT EXISTS Answers  (
  qid integer(8),
  aid integer(2),
  primary key (qid,aid),
  image varchar(256),
  text varchar(256),
  correct bit,
  response varchar(256) -- information to display if this answer selected
);

CREATE TABLE IF NOT EXISTS Quizzes  (
  quizid integer(8),
  -- TODO: what about ordering quizzes in the display? some sequence?
  -- right now, I use a letter code to make sure it sorts right (see QuizDev)
  name   varchar(256),  -- not sure if we need this? quizid is the unique id
  title  varchar(256),
  instructions varchar(256)
);

CREATE TABLE IF NOT EXISTS QuizzesMatch  (
  quizid integer(8),
  qid integer(8),
  questionOrder integer(4)
);



--insert into questions values(


--SELECT qid, title, image, text from questions, questionM WHERE 