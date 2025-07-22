function generteOtp(length) {
    let  otp=''
    for (let index = 0; index < length; index++) {
        otp+= Math.floor(Math.random()*10).toString();
        
    }
    return otp;
}

console.log(generteOtp(6))