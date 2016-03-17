package com.example.wheelviewtest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.wheelview.listener.OnConfirmClickForWheel;
import com.example.wheelview.view.WheelDatePopup;

public class MainActivity extends Activity {

	private TextView daTextView;
	private List<String> list = new ArrayList<String>();
	
	private WheelDatePopup datePopup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Calendar calendar = Calendar.getInstance();
		int curr = calendar.get(Calendar.YEAR);
		for (int i = 1941; i < curr+1; i++) {
			list.add(i+"");
		}
		
		daTextView = (TextView) findViewById(R.id.date);
		daTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showPopuView();
			}
		});
		
	}

	public void showPopuView() {
		datePopup = new WheelDatePopup(MainActivity.this, list);
		datePopup.showPopupWindow();
		datePopup.setBuConfirmClick(new OnConfirmClickForWheel() {
			
			@Override
			public void onConfirmClickForWheel(String val) {
				daTextView.setText(val);
			}
		});
	}

}
