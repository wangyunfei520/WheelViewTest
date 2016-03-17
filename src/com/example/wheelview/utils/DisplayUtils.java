package com.example.wheelview.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayUtils {
	
	/** 
	* 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
	*/  
	public static int dip2px(Context context, float dpValue) {  
	  final float scale = context.getResources().getDisplayMetrics().density;  
	  return (int) (dpValue * scale + 0.5f);  
	}  
	  
	/** 
	* 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
	*/  
	public static int px2dip(Context context, float pxValue) {  
	  final float scale = context.getResources().getDisplayMetrics().density;  
	  return (int) (pxValue / scale + 0.5f);  
	}  
	 /** 
     * 将px值转换为sp值，保证文字大小不变 
     *  
     * @param pxValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     * @param spValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int sp2px(Context context, float spValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }  
    
    public static Boolean initView(String str,TextView tv){
    	if(str!=null&&str.length()>0&&tv!=null){
    		tv.setText(str.trim());
    		return true;
    	}
    	return false;
    }
    public static Boolean initView(String str,EditText et){
    	
    	if(str!=null&&str.length()>0&&et!=null){
    		et.setText(str.trim());
    		return true;
    	}
    	return false;
    }
    public static void showToast(Context context,String text){
    	Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		DisplayUtils.dip2px(context, 30);
		toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 200);
		   toast.show();
    }

}

