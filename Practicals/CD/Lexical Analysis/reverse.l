%{
#include <stdio.h>
#include <string.h>
char str[100];
%}

%%
[a-zA-Z]+   { strcpy(str, yytext); }
\n          ;
.           ;
%%

int main() {
    yylex();

    int len = strlen(str);

    printf("Reverse String: ");
    for(int i = len-1; i >= 0; i--)
        printf("%c", str[i]);

    printf("\n");
    return 0;
}

int yywrap() { return 1; }
