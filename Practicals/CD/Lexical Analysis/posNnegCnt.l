%{
#include <stdio.h>
int pos = 0, neg = 0;
%}

%%
[+]?[0-9]+      { pos++; }
-[0-9]+         { neg++; }
\n              ;
.               ;
%%

int main() {
    yylex();
    printf("Positive Integers: %d\n", pos);
    printf("Negative Integers: %d\n", neg);
    return 0;
}

int yywrap() {
    return 1;
}
