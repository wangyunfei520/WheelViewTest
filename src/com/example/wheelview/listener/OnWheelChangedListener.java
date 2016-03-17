
package com.example.wheelview.listener;

import com.example.wheelview.view.WheelView;

public interface OnWheelChangedListener {
	
	void onChanged(WheelView wheel, int oldValue, int newValue);
}
