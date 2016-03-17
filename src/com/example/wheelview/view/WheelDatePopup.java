package com.example.wheelview.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wheelview.listener.ClickListener;
import com.example.wheelview.listener.OnConfirmClickForWheel;
import com.example.wheelview.listener.OnWheelChangedListener;
import com.example.wheelview.listener.OnWheelScrollListener;
import com.example.wheelviewtest.R;

public class WheelDatePopup {

	private String[] yearsStrings,yearsStrings1;
	private Context mContext;
	
	private OnConfirmClickForWheel mOncliClickForWheel;

	private View popupView, closeView;
	private PopupWindow popupWindow;
	private ImageView ivClose;
	private TextView tvConfirm;
	private WheelView centerView, leftView, rightView;

	private boolean wheelScrolled = false;
	private String[] mouthStrings = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	private String[] mouthStrings1 = { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月",
			"9月", "10月", "11月", "12月" };
	private String[] daysStrings;

	private ArrayWheelAdapter<String> arrayWheelLeftAdapter,
			arrayWheelCenterAdapter, arrayWheelRightAdapter;

	private ClickListener clickListener;
	private OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		@Override
		public void onScrollingStarted(WheelView wheel) {
			wheelScrolled = true;
		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			wheelScrolled = false;
			updateStatus(wheel);
		}
	};
	private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			if (!wheelScrolled) {
				updateStatus(wheel);
			}
		}
	};

	public void updateStatus(WheelView wheel) {
		if (wheel == leftView) {
			updateDay();
		} else if (wheel == centerView) {
			updateDay();
		}
	}

	public void setBuConfirmClick(
			final OnConfirmClickForWheel oncliClickForWheel) {
		this.mOncliClickForWheel = oncliClickForWheel;
		tvConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String show = yearsStrings[leftView.getCurrentItem()] + "年  "
						+ mouthStrings[centerView.getCurrentItem()] + "月  "
						+ daysStrings[rightView.getCurrentItem()];
				mOncliClickForWheel.onConfirmClickForWheel(show);
				popupWindow.dismiss();
			}
		});
		
	}
	
	private void updateDay() {
		int index = leftView.getCurrentItem();
		int year = Integer.parseInt(yearsStrings[index]);
		int mouth = Integer.parseInt(mouthStrings[centerView.getCurrentItem()]);
		int day = 0;
		switch (mouth) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;

		case 2:
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;

		default:
			break;
		}
		daysStrings = new String[day];
		for (int i = 1; i < day + 1; i++) {
			daysStrings[i - 1] = i + "日";
		}
		rightView.setAdapter(new ArrayWheelAdapter<String>(daysStrings));
	}

	public WheelDatePopup(Context context, List<String> yearList) {
		this.mContext = context;
		yearsStrings = new String[yearList.size()];
		yearsStrings1 = new String[yearList.size()];
		for (int i = 0; i < yearList.size(); i++) {
			yearsStrings[i] = yearList.get(i);
			yearsStrings1[i] = yearList.get(i)+"年";
		}
		daysStrings = new String[31];
		for (int i = 1; i < 32; i++) {
			daysStrings[i - 1] = i + "日";
		}
		createPopupView();
	}

	@SuppressLint("InflateParams")
	public void createPopupView() {
		popupView = LayoutInflater.from(mContext).inflate(
				R.layout.wheel_popwindow, null, false);
		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		closeView = popupView.findViewById(R.id.view_close_wheel);
		ivClose = (ImageView) popupView.findViewById(R.id.iv_close_wheel);
		tvConfirm = (TextView) popupView.findViewById(R.id.tv_confirm_wheel);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		popupWindow.setOutsideTouchable(true);
		popupWindow.setContentView(popupView);
		centerView = (WheelView) popupView.findViewById(R.id.passw_center);
		rightView = (WheelView) popupView.findViewById(R.id.passw_right);
		leftView = (WheelView) popupView.findViewById(R.id.passw_left);

		arrayWheelLeftAdapter = new ArrayWheelAdapter<String>(yearsStrings1);
		arrayWheelCenterAdapter = new ArrayWheelAdapter<String>(mouthStrings1);
		arrayWheelRightAdapter = new ArrayWheelAdapter<String>(daysStrings);
		initWheel(1,leftView, arrayWheelLeftAdapter);
		initWheel(2,centerView, arrayWheelCenterAdapter);
		initWheel(3,rightView, arrayWheelRightAdapter);

		clickListener = new ClickListener(mContext) {
			@Override
			public void onClick(View v) {
				super.onClick(v);
				popupWindow.dismiss();
			}
		};
		closeView.setOnClickListener(clickListener);
		ivClose.setOnClickListener(clickListener);
	}

	public void initWheel(int i, WheelView wheel,
			ArrayWheelAdapter<String> adapter) {
		wheel.setAdapter(adapter);
		wheel.addChangingListener(changedListener);
		wheel.addScrollingListener(scrolledListener);
		wheel.setVisibleItems(5);
		wheel.setCyclic(false);
		wheel.setInterpolator(new AnticipateOvershootInterpolator());
		switch (i) {
		case 1:
			wheel.setAlignmentLR(false);
			break;

		case 2:
			wheel.setAlignmentCenter(true);
			break;

		case 3:
			wheel.setAlignmentLR(true);
			break;

		default:
			break;
		}
	}

	public void showPopupWindow() {
		popupWindow.showAsDropDown(popupView);
	}

	public void showPopupWindow(int left, int center, int right) {
		leftView.setCurrentItem(left);
		centerView.setCurrentItem(center);
		rightView.setCurrentItem(right);
		popupWindow.showAsDropDown(popupView);
	}
}
