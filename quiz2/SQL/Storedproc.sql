use quiz;
DELIMITER // 

DROP PROCEDURE IF EXISTS GetAllQuizzes; //
CREATE PROCEDURE GetAllQuizzes() 
BEGIN 
  SELECT quizid, name, title FROM Quizzes; 
END // 

DROP PROCEDURE IF EXISTS AddQuestion; //
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddQuestion`(IN uquizid INTEGER(8),
                             IN qtype INTEGER(4),
                             IN title VARCHAR(255),
                             IN image VARCHAR(255),
                             IN text  VARCHAR(255))
BEGIN 
  DECLARE last INT;
SELECT 
    MAX(questionOrder)
FROM
    Questions,
    QuizzesQuestionsOrder
WHERE
    quizid = uquizid INTO last; 
  INSERT INTO Questions VALUES(last+1, qtype, 1, title, image, text);
END // 

DROP PROCEDURE IF EXISTS TakeQuiz; //

CREATE DEFINER=`root`@`localhost` PROCEDURE `TakeQuiz`(IN quizid INTEGER(8))
BEGIN 
  SELECT Questions.qid, title, level FROM Questions,QuizzesQuestionsOrder WHERE
    QuizzesQuestionsOrder.quizid = quizid and QuizzesQuestionsOrder.qid = Questions.qid;
SELECT 
    Answers.qid, aid, Answers.image, Answers.text, correct, response
FROM
    Answers,
    Questions,
    QuizzesQuestionsOrder
WHERE
    QuizzesQuestionsOrder.quizid = quizid
        AND Answers.qid = Questions.qid
        AND Questions.qid = QuizzesQuestionsOrder.qid
ORDER BY QuizzesQuestionsOrder.questionOrder;
END //

DELIMITER ;

/*
CREATE PROCEDURE UpdateQuizNames(quizid, newtitle) 
BEGIN 
  UPDATE Quizzes SET title = newtitle WHERE Quizzes.quizid = quizid; 
END // 


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