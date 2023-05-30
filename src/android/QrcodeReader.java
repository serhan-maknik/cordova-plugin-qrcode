package cordova.qrcode.plugin;

import android.graphics.Bitmap;
import android.util.Base64;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * This class echoes a string called from JavaScript.
 */
public class QrcodeReader extends CordovaPlugin {

    CallbackContext callbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("qrcode")) {
            String message = args.getString(0);
            JSONObject jsonObject  = new JSONObject(message);
            JSONObject data = jsonObject.optJSONObject("data");
            String qrval = data.optString("wifiSetting");
            qrCodeGenerator(qrval);

            return true;
        }
        return false;
    }
    
    public void qrCodeGenerator(String qrcode){

        MultiFormatWriter mWriter = new MultiFormatWriter();
        try {
            //BitMatrix class to encode entered text and set Width & Height
            BitMatrix mMatrix = mWriter.encode(qrcode, BarcodeFormat.QR_CODE, 400,400);
            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
            String base64 = convert(mBitmap);
            this.callbackContext.success(base64);
        } catch (WriterException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }
    }
    public String convert(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
}
