use quiz;

DROP TABLE Questions;
DROP TABLE Answers;
DROP TABLE Quizzes;
DROP TABLE QuizzesQuestionsOrder;

CREATE TABLE IF NOT EXISTS Questions  (
  qid integer(8) primary key,
  qtype integer(4),
  level integer(2),
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
  correct char(1) NOT NULL DEFAULT 'N',
  response varchar(256) -- information to display if this answer selected
);

CREATE TABLE IF NOT EXISTS Quizzes  (
  quizid integer(8),
  -- TODO: what about ordering quizzes in the display? some sequence?
  -- right now, I use a letter code to make sure it sorts right (see QuizDev)
  name   varchar(255),  -- not sure if we need this? quizid is the unique id
  title  varchar(255),
  instructions varchar(255)
);

CREATE TABLE IF NOT EXISTS QuizzesQuestionsOrder  (
  quizid integer(8),
  qid integer(8),
  questionOrder integer(4)
);



INSERT INTO Questions VALUES(1, 1, 1, 'Dynosaur', NULL, 'What is a Tyranosaurus Rex?');
INSERT INTO Answers VALUES(1, 1, NULL, 'A dynosaur', 1, NULL);
INSERT INTO Answers VALUES(1, 2, NULL, 'A fish', 0, NULL);
INSERT INTO Answers VALUES(1, 3, NULL, 'A primate', 0, NULL);
INSERT INTO Answers VALUES(1, 4, NULL, 'A mammal', 0, NULL);

INSERT INTO Questions VALUES(2, 2, 1, 'Dynosaur', NULL, 'Which is an insect?');
INSERT INTO Answers VALUES(2, 1, NULL, 'spider', 0, 'Sorry, a spider is not an insect');
INSERT INTO Answers VALUES(2, 2, NULL, 'fly', 1, NULL);
INSERT INTO Answers VALUES(2, 3, NULL, 'grasshopper', 1, NULL);
INSERT INTO Answers VALUES(2, 4, NULL, 'monkey', 0, 'No, it''s a mammal');

INSERT INTO Quizzes VALUES(1, 'animals', 'Animals Quiz', 'Answer these fun questions to test your knowledge of animals');
INSERT INTO Quizzes VALUES(2, 'IntroJava', 'Java Data Types', 'Test your knowledge of Basic Java Data Types');
INSERT INTO Quizzes VALUES(3, 'IntroC++', 'C++ Data Types', 'Test your knowledge of Basic C++ Data Types');
INSERT INTO Quizzes VALUES(4, 'AdvancedC++', 'C++ Details', 'Test your knowledge of Arcane C++ Minutia');

INSERT INTO QuizzesQuestionsOrder VALUES(1, 1, 1);
INSERT INTO QuizzesQuestionsOrder VALUES(1, 2, 2);


