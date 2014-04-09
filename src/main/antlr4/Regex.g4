grammar Regex;

 @header{
 	package main.java.antlr4;
 }
 
expression:
    ( parenExpression 
    | multipleExpressionStar
    | multipleExpressionPlus
    | symbolExpression )*
    (AND expression)?
    
;



parenExpression:
	LPAREN expression RPAREN
;	



multipleExpressionStar:
	multipeExpressionGeneral STAR
;


multipleExpressionPlus:
	multipeExpressionGeneral PLUS
;

multipeExpressionGeneral:
	(
	parenExpression
	|
	symbolExpression
	) 
;

symbolExpression:
	SYMBOL
;

//
// Lexer Rules

AND:'|';
RPAREN: ')' ;
LPAREN: '(' ;
PLUS:'+';
STAR:'*';
SYMBOL:('a'..'z')|('A'..'Z');