/**
 * Copyright 2014-present Ajay Sahani
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ajra.multiactiontextview;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * Original ---
 * @author Ajay Sahani:
 * @seeA :Class responsible for hiding and showing and Setting Link to
 *       substring.
 * 
 */

public abstract class MultiActionTextViewClickableSpan extends ClickableSpan {
	
	/**
	 * 
	 * Modified @20150416 by Smilee Yang
	 * 
	 * @param mIsPressed
	 * : Detected hyper link has pressed or not by using LinkTouchMovementMethod class method.
	 * 
	 * @param isUnderLineRequired
	 * : Config hyper link with/without under line.
	 * 
	 * @param defaultHyperLinkColor
	 * : Config hyper link default color.
	 * 
	 * @param pressedHyperLinkColor
	 * : Config hyper link pressed color.
	 * 
	 */

	private boolean mIsPressed;
	private boolean isUnderLineRequired;
	private int defaultHyperLinkColor, pressedHyperLinkColor;

	public MultiActionTextViewClickableSpan(InputObject inputObject) {
		this.isUnderLineRequired = inputObject.getIsUnderLineRequired();
		this.defaultHyperLinkColor = inputObject.getDefaultHyperLinkColor();
		this.pressedHyperLinkColor = inputObject.getPressedHyperLinkColor();
	}

	public void setPressed(boolean isSelected) {
		mIsPressed = isSelected;
	}

	@Override
	public void updateDrawState(TextPaint ds) {
		ds.setColor(mIsPressed ? pressedHyperLinkColor : defaultHyperLinkColor);
		ds.setUnderlineText(isUnderLineRequired);
		ds.clearShadowLayer();
	}
}
