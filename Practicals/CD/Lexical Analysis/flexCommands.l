%{
#include <stdio.h>
%}

%%
[a-zA-Z_][a-zA-Z0-9_]*   { printf("Identifier: %s\n", yytext); }
[0-9]+                  { printf("Number: %s\n", yytext); }
[ \t\n]+                ;
.                       { printf("Special Character: %s\n", yytext); }
%%

int yywrap() {
    return 1;
}


int main() {
    printf("Enter source code: ");
    yylex();
    return 0;
}