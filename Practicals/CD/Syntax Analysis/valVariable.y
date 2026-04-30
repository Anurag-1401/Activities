%{
#include <stdio.h>

int yylex();
void yyerror(const char *s);

%}

%token ID

%%

var: ID { printf("Valid Variable\n"); }
   ;

%%

int main() {
    printf("Enter variable: ");
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    printf("Invalid Variable\n");
}