%{
#include <stdio.h>
%}

%%
[a-zA-Z_][a-zA-Z0-9_]*   { printf("Identifier: %s\n", yytext); }
[+\-*/=]                 { printf("Operator: %s\n", yytext); }
[0-9]+                   ;
[ \t\n]                  ;
.                        { printf("Invalid Symbol: %s\n", yytext); }
%%

int main() {
    yylex();
    return 0;
}

int yywrap() { return 1; }
