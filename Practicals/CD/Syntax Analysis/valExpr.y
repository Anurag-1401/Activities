%{
#include <stdio.h>

int yylex();
void yyerror(const char *s);
%}

%token NUM

%left '+' '-'
%left '*' '/'

%%

input: expr { printf("Valid Expression\n"); }
     ;

expr: expr '+' expr
    | expr '-' expr
    | expr '*' expr
    | expr '/' expr
    | NUM
    ;

%%

int main() {
    printf("Enter expression: ");
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    printf("Invalid Expression\n");
}