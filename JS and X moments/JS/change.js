let obj = {
    name:"andy",
    number:1234567890,
    ac_no: 109877890,
}

Object.defineProperty(obj,"ac_no",{
    writable:false,
})

obj.ac_no = 10
console.log(obj.ac_no)

let a = [1,2,3,4,5,6,7,8,9]
console.log(a.filter(b=>b%2==0).map(c=>c*c))

let b = [1,2,3,3,4,2,1,5,8,9,0]
console.log([...new Set(b)])