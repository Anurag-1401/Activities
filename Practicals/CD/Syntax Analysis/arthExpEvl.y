%{
#include <stdio.h>
#include <stdlib.h>

int yylex();
void yyerror(const char *s);
%}

%token NUM

%left '+' '-'
%left '*' '/'

%%

input: expr { printf("Result = %d\n", $1); }
     ;

expr: expr '+' expr { $$ = $1 + $3; }
    | expr '-' expr { $$ = $1 - $3; }
    | expr '*' expr { $$ = $1 * $3; }
    | expr '/' expr { $$ = $1 / $3; }
    | NUM           { $$ = $1; }
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