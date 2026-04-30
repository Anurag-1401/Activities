%{
#include <stdio.h>
int chars = 0, words = 0, spaces = 0, lines = 0;
%}

%%
\n          { lines++; chars++; }
[ \t]      { spaces++; chars++; }
[a-zA-Z0-9]+ { words++; chars += yyleng; }
.           { chars++; }
%%

int main() {
    yylex();
    printf("Characters: %d\n", chars);
    printf("Words: %d\n", words);
    printf("Spaces: %d\n", spaces);
    printf("Lines: %d\n", lines);
    return 0;
}

int yywrap() {
    return 1;
}
