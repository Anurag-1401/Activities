%{
#include "nestIfLevelDet.tab.h"

extern int level;
extern int maxLevel;
%}

%%

"if" {
    level++;
    if(level > maxLevel) maxLevel = level;
    return IF;
}

"else" {
    level--;   // reduce level when else appears
    return ELSE;
}

[a-zA-Z]+   return ID;
[ \t\n]     ;
.           return yytext[0];

%%

int yywrap() {
    return 1;
}