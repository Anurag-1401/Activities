%{
#include <stdio.h>
int count = 0;
%}

%%
[a-zA-Z_][a-zA-Z0-9_]*   { count++; }
\n                       ;
.                        ;
%%

int main() {
    yylex();
    printf("Number of Identifiers: %d\n", count);
    return 0;
}

int yywrap() { return 1; }
