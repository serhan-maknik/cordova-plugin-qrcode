declare const QrcodeReader:QrcodeReader;


interface QrcodeReader {
    getQrCode(
        data:any,
        successCallback:()=>void,
        errorCallback:()=>void,
    ):void,

   
}