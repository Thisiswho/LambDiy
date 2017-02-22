package tools;

import android.content.res.AssetManager;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2017/1/22.
 * 自己写的工具类，各种类型的数据转换。
 */
public class MyTools {

    public Drawable bytes2Drawable(byte[] b){
        Bitmap bitmap = this.bytes2Bitmap(b);
        return this.bitmap2Drawable(bitmap);
    }

    public static Bitmap bytes2Bitmap(byte[] b){
        if(b.length !=0){
            return BitmapFactory.decodeByteArray(b,0,b.length);
        }
        return null;
    }

    public static Bitmap Drawable2Bitmap(Drawable drawable){
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),
                                            drawable.getOpacity()!= PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] Bitmap2ByteArray(Bitmap bmp){
        int bytes = bmp.getByteCount();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes);
        bmp.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        return byteArray;
    }

    public static Drawable bitmap2Drawable(Bitmap bmp){
        BitmapDrawable bd = new BitmapDrawable(bmp);
        Drawable d = bd;
        return d;
    }

    public static int randomColor(){
        Random random = new Random();
        int color = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
        return color;
    }

    public static HashMap string2HashMap(String str){
        if(str == null || str == "")
            return null;
        str = str.substring(1);
        str = str.substring(0,str.length()-1);
        String[] strings = str.split(",");
        HashMap resultMap = new HashMap();
        for(String entry : strings){
            String[] temp = entry.split("=");
            resultMap.put(temp[0].trim(),temp[1].trim());
        }
        return resultMap;
    }

    public static byte[] Image2ByteArray(Uri uri){
        File file = new File(URI.create(uri.toString()));
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readNum;(readNum = fis.read(buf)) != -1;){
                baos.write(buf,0,readNum);
            }
            byte[] bytes = baos.toByteArray();

            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static Drawable loadAssets2Drawable(AssetManager assetManager,String filename){
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = assetManager.open(filename);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap2Drawable(bitmap);
    }
}
