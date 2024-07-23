grammar SimpleExpression;
import SimpleTokens;

line
    : exp EOF
    ;

exp
    : expression
    ;

expression
    : string                                    # StringExpr
    | number                                    # NumberExpr
    | id                                        # IdExpr
    | (LPAREN expression RPAREN)                # ParenExpr
    | expression DIV expression    # DivExpr
    | expression MUL expression                 # MulExpr
    | expression op=(ADD|SUB) expression        # AddSubExpr
    | expression op=(GTE|GT|LTE|LT|EQ|NEQ) expression   # RelExpr
    | NOT expression                            # NotExpr
    | expression AND expression                 # AndExpr
    | expression OR expression                  # OrExpr
    ;


string
    : STRINGLITERAL
    ;

number
    : NUMBER
    ;

id
    : ID
    ;


