# cordova-plugin-qrcode

## HTML

```html
<body>
    
         <div style="display: flex;flex-direction: column;">
             <button onclick="handleSubmit()">Qrcode</button>
             <img id="myImage" style="width: 120px; height: 120px;"></img>
          </div>
  

 <script>
        function handleSubmit() {
            var qrval = `type-wifi/ssid-maknik/password-1234/staticip-192.168.1.2`
          
            QrcodeReader.getQrCode({
                wifiSetting:qrval
            },function (imageData){
                var image = document.getElementById('myImage');
                   image.src = "data:image/jpeg;base64," + imageData;
            },
            
            function (data){
                
            }
            )
        }
    </script>
</body>

```
## javascript
```js
QrcodeReader.getQrCode({
    wifiSetting:"type-wifi/ssid-maknik/password-1234/staticip-192.168.1.2"  // Example
},function (imageData){
    var image = document.getElementById('myImage');
       image.src = "data:image/jpeg;base64," + imageData;
},

function (error){
    console.log(error)
}
)
```
