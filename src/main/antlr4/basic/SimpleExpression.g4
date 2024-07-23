grammar SimpleExpression;
import SimpleTokens;

line
    : expression EOF
    ;

expression
    : boolean_expression
    ;

boolean_expression
    : string_expression EQ string_expression #StringEqExp
    | number_expression EQ number_expression #NumberEqExp
    ;

string_expression
    : string_expression ADD string_expression #StringPlusExp
    | string #StringExp
    ;

number_expression
    : number_expression MUL number_expression   #NumberMulExp
    | number_expression ADD number_expression  #NumberPluxExp
    | number #NumberExp
    ;

string
    : STRINGLITERAL
    ;

number
    : NUMBER
    ;



