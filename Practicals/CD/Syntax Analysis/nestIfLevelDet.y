%{
#include <stdio.h>

int level = 0;
int maxLevel = 0;

int yylex();
void yyerror(const char *s);
%}

%token IF ELSE ID
%nonassoc LOWER_THAN_ELSE
%nonassoc ELSE

%%

stmt: matched_stmt
    | unmatched_stmt
    ;

matched_stmt:
      IF '(' ID ')' stmt ELSE stmt
    | ID
    ;

unmatched_stmt:
      IF '(' ID ')' stmt %prec LOWER_THAN_ELSE
    ;

%%

int main() {
    printf("Enter statement:\n");
    yyparse();
    printf("Max Nesting Level = %d\n", maxLevel);
    return 0;
}

void yyerror(const char *s) {
    printf("Error\n");
}