%{
#include <stdio.h>
int count = 0;
%}

%%
"//".*              { count++; }          /* single-line comment */
"/*"([^*]|\*+[^/])*"*/"   { count++; }    /* multi-line comment */
\n                  ;
.                   ;
%%

int main() {
    yylex();
    printf("Number of comment lines: %d\n", count);
    return 0;
}

int yywrap() {
    return 1;
}