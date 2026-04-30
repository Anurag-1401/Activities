%{
#include <stdio.h>
int flag = 0;
%}

%%
[a-zA-Z]+[ ]+[a-zA-Z]+   { flag = 1; }
\n                       ;
.                        ;
%%

int main() {
    printf("Enter sentence:\n");
    yylex();

    if(flag)
        printf("The given sentence is Compound sentence\n");
    else
        printf("The given sentence is Simple sentence\n");

    return 0;
}

int yywrap() {
    return 1;
}