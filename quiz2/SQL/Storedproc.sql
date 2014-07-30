DELIMITER // 
CREATE PROCEDURE GetAllQuizzes() 
BEGIN 
SELECT quizid, name, title FROM Quizzes; 
END // 


/*
CREATE PROCEDURE insertQuestion() 
BEGIN 

END // 


CREATE PROCEDURE ModifyQuiz(quizid, ...)
BEGIN 
   UPDATE ...
END // 

CREATE PROCEDURE (quizid, ...)
BEGIN 
   UPDATE ...
END // 
   
CREATE PROCEDURE listQuestions(quizid)
BEGIN 
	SELECT ...   FROM Questions, QuizzesMatch
	   WHERE quizid = QuizzesMatch.quizid AND Questions.qid = QuizzesMatch.qid
	
	SELECT ... FROM Answers, Questions WHERE Questions.qid = Answers.qid
END // 
   
*/










































DELIMITER ; 