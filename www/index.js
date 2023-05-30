const exec=require("cordova/exec");


module.exports={
    getQrCode:(data,successCallback,errorCallback)=>{
        const obj={
            data:data
        };
        exec(successCallback,errorCallback,"QrcodeReader","qrcode",[obj]);
    },
    
}
