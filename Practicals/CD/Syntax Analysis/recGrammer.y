%{
#include <stdio.h>

int countA = 0;
int errorFlag = 0;

int yylex();
void yyerror(const char *s);
%}

%token A B

%%

S: A S { countA++; }
 | B
 ;

%%

int main() {
    printf("Enter string: ");
    yyparse();

    if(errorFlag == 0 && countA >= 10)
        printf("Valid String\n");
    else if(errorFlag == 0)
        printf("Invalid: Less than 10 A's\n");

    return 0;
}

void yyerror(const char *s) {
    errorFlag = 1;
    printf("Invalid String\n");
}