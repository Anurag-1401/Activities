%{
#include <stdio.h>
int posf = 0, negf = 0;
%}

%%
[+]?[0-9]*"."[0-9]+   { posf++; }
-[0-9]*"."[0-9]+      { negf++; }
\n                    ;
.                     ;
%%

int main() {
    yylex();
    printf("Positive Fractions: %d\n", posf);
    printf("Negative Fractions: %d\n", negf);
    return 0;
}

int yywrap() {
    return 1;
}
