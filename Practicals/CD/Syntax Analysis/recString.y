%{
#include <stdio.h>

int yylex();
void yyerror(const char *s);

int errorFlag = 0;
%}

%token A B

%%

S: A S B
 | 
 ;

%%

int main() {
    printf("Enter string: ");
    yyparse();

    if(errorFlag == 0)
        printf("Valid String\n");
    else
        printf("Invalid String\n");

    return 0;
}

void yyerror(const char *s) {
    errorFlag = 1;
}