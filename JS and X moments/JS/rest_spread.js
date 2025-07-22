function greet(...names){ //rest
    for(let n of names){
        console.log(n)
    }
}

greet("andy","aman","anurag")

let a = [1,2,3];
let b = [...a ,4,5]; //[a ,4,5]
console.log(b)