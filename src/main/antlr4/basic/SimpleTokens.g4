lexer grammar SimpleTokens; // note "lexer grammar"

// operators
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
MOD : '%' ;

// logical
NEQ : '<>' ;
GTE : '>=' ;
LTE : '<=' ;
GT  : '>' ;
LT  : '<' ;
EQ  : '=' ;

// relational
AND : 'AND' | 'and' ;
OR  : 'OR' | 'or' ;
NOT : 'NOT' | 'not' ;

// other
COMMA  : ',' ;
LPAREN : '(' ;
RPAREN : ')' ;

START_ARRAY : '[' ;

END_ARRAY : ']' ;

IN : 'in' ;


// literals
ID              : [a-zA-Z]+ ;  // match identifiers
NUMBER          : [0-9]+ ('.' [0-9]+)?;   // match integers
STRINGLITERAL   : '"' ~ ["\r\n]* '"' ;

WS              : [ \t]+ -> skip ; // toss out whitespace

