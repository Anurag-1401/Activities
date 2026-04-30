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
    int flag = 1;

    for(int i = 0; i < len/2; i++) {
        if(str[i] != str[len-i-1]) {
            flag = 0;
            break;
        }
    }

    if(flag)
        printf("Palindrome\n");
    else
        printf("Not Palindrome\n");

    return 0;
}

int yywrap() { return 1; }
