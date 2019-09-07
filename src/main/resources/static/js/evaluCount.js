function keypress1(){
    var text1=document.getElementById("myText").value;
    var maxLen=document.getElementById("myText").maxLength;
    var len=maxLen-text1.length;
    document.getElementById("name").innerText=len;
}

function keypress2(){
    var text1=document.getElementById("myArea").value;
    var maxLen=document.getElementById("myArea").cols*document.getElementById("myArea").rows;
    var len;//记录剩余字符串的长度
    if(text1.length>=maxLen)
    {
        document.getElementById("myArea").value=text1.substr(0,maxLen);//只显示起始位-末尾；substr（起始位，末尾)
        len=0;
    }else{
        len=maxLen-text1.length;
    }
    document.getElementById("pinglun").innerText=len;
}

function textCounter(field, maxlimit) {
    if (field.value.length > maxlimit){
        field.value = field.value.substring(0, maxlimit);
    }else{
        document.upbook.remLen.value = maxlimit - field.value.length;
    }
}

function LimitTextArea(field){
    maxlimit=500;
    if (field.value.length > maxlimit)
        field.value = field.value.substring(0, maxlimit);
}