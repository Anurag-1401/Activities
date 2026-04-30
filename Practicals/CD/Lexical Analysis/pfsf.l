%{
#include <stdio.h>

int scanf_count = 0;
int printf_count = 0;
%}

%%
scanf      { scanf_count++; printf("readf"); }
printf     { printf_count++; printf("writef"); }
.|\n       { ECHO; }
%%

int main() {
    yylex();
    printf("\nScanf Count: %d\n", scanf_count);
    printf("Printf Count: %d\n", printf_count);
    return 0;
}

int yywrap() {
    return 1;
}