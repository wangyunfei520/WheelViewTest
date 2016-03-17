package com.example.wheelview.listener;


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * µ¥»÷¼àÌý
 * @author 
 *
 */
public  class ClickListener implements OnClickListener {
	private Context context;
	
	public ClickListener(Context context) {
		super();
		this.context = context;
	}
	
	@Override
	public void onClick(View v) {
		if(context==null){
			return ;
		}
//		Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
//		long[] pattern = {800, 50, 400, 30}; // OFF/ON/OFF/ON...   
//		vibrator.vibrate(40);//µã»÷Õð¶¯
		
		
	}

}
