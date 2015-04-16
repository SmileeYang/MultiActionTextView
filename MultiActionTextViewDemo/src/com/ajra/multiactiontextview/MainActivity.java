package com.ajra.multiactiontextview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Smilee modified for showing update this library usage @20150416
 * 
 * */

public class MainActivity extends Activity {

	private TextView tvWithoutUnderLineWithBGColor;
	private TextView tvWithoutUnderLineWithTransparentBGColor;
	private TextView tvWithUnderLineAndTransparentBGColor;
	private MyMultiActionClickListener myMultiActionClickListener;
	private final int NAME_CLICKED = 1;
	private final int ACTION_CLICKED = 2;
	private final int CONTENT_CLICKED = 3;
	private final String name = "Smilee";
	private final String action = "modified";
	private final String contentName = "TextViewClickableSpan";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvWithoutUnderLineWithBGColor = (TextView) findViewById(R.id.multi_action_textview);
		tvWithoutUnderLineWithTransparentBGColor = (TextView) findViewById(R.id.multi_action_textview_link_few);
		tvWithUnderLineAndTransparentBGColor = (TextView) findViewById(R.id.multi_action_textview_link);

		myMultiActionClickListener = new MyMultiActionClickListener();
		withoutUnderLineALLClickablePlusDifferentBackgroundColor();
		withoutUnderLineInFewClickableStringPlusTransparentBackgroundColor();
		withUnderLineInFewClickableStringPlusTransparentBackgroundColor();
	}

	private void withoutUnderLineALLClickablePlusDifferentBackgroundColor() {
		SpannableStringBuilder stringBuilder = getStringBuilder();
		// name click
		InputObject nameClick = getNameClickObject(stringBuilder, false, Color.YELLOW, Color.BLACK);
		MultiActionTextView.addActionOnTextView(nameClick);

		// action click
		InputObject actionClick = getActionClickObject(stringBuilder, false, Color.YELLOW, Color.BLACK);
		MultiActionTextView.addActionOnTextView(actionClick);

		// content name click
		InputObject contentClick = getContentClickObject(stringBuilder, false, Color.YELLOW, Color.BLACK);
		MultiActionTextView.addActionOnTextView(contentClick);

		// final step
		MultiActionTextView.setSpannableText(tvWithoutUnderLineWithBGColor, stringBuilder, Color.WHITE);
	}

	private void withoutUnderLineInFewClickableStringPlusTransparentBackgroundColor() {
		SpannableStringBuilder stringBuilder = getStringBuilder();
		// name click
		InputObject nameClick = getNameClickObject(stringBuilder, false, Color.RED, Color.YELLOW);
		MultiActionTextView.addActionOnTextView(nameClick);

		// content name click
		InputObject contentClick = getContentClickObject(stringBuilder, false, Color.YELLOW, Color.RED);
		MultiActionTextView.addActionOnTextView(contentClick);

		// final step
		MultiActionTextView.setSpannableText(tvWithoutUnderLineWithTransparentBGColor, stringBuilder, Color.TRANSPARENT);
	}

	private void withUnderLineInFewClickableStringPlusTransparentBackgroundColor() {
		SpannableStringBuilder stringBuilder = getStringBuilder();
		// name click
		InputObject nameClick = getNameClickObject(stringBuilder, true, Color.GREEN, Color.WHITE);
		nameClick.setIsUnderLineRequired(true);
		MultiActionTextView.addActionOnTextView(nameClick);

		// content name click
		InputObject contentClick = getContentClickObject(stringBuilder, true, Color.GREEN, Color.WHITE);
		contentClick.setIsUnderLineRequired(true);
		MultiActionTextView.addActionOnTextView(contentClick);

		// final step
		MultiActionTextView.setSpannableText(tvWithUnderLineAndTransparentBGColor, stringBuilder, Color.TRANSPARENT);
	}
	
	private SpannableStringBuilder getStringBuilder() {
		SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
		stringBuilder.append(name);
		stringBuilder.append(" ");
		stringBuilder.append(action);
		stringBuilder.append(" ");
		stringBuilder.append(contentName);
		return stringBuilder;
	}
	
	private InputObject getNameClickObject(SpannableStringBuilder stringBuilder, boolean isUnderLineRequired, int defaultColor, int pressedColor) {
		int startSpan = 0;
		int endSpan = name.length();
		InputObject nameClick = new InputObject();
		nameClick.setStartSpan(startSpan);
		nameClick.setEndSpan(endSpan);
		nameClick.setStringBuilder(stringBuilder);
		nameClick.setMultiActionTextviewClickListener(myMultiActionClickListener);
		nameClick.setOperationType(NAME_CLICKED);
		nameClick.setIsUnderLineRequired(isUnderLineRequired);
		nameClick.setDefaultHyperLinkColor(defaultColor);
		nameClick.setPressedHyperLinkColor(pressedColor);
		return nameClick;
	}
	
	private InputObject getActionClickObject(SpannableStringBuilder stringBuilder, boolean isUnderLineRequired, int defaultColor, int pressedColor) {
		int startSpan = name.length() + 1;
		int endSpan = startSpan + action.length();
		InputObject actionClick = new InputObject();
		actionClick.setStartSpan(startSpan);
		actionClick.setEndSpan(endSpan);
		actionClick.setStringBuilder(stringBuilder);
		actionClick.setMultiActionTextviewClickListener(myMultiActionClickListener);
		actionClick.setOperationType(ACTION_CLICKED);
		actionClick.setIsUnderLineRequired(isUnderLineRequired);
		actionClick.setDefaultHyperLinkColor(defaultColor);
		actionClick.setPressedHyperLinkColor(pressedColor);
		return actionClick;
	}
	
	private InputObject getContentClickObject(SpannableStringBuilder stringBuilder, boolean isUnderLineRequired, int defaultColor, int pressedColor) {
		int startSpan = name.length() + action.length() + 2;
		int endSpan = startSpan + contentName.length();
		InputObject contentClick = new InputObject();
		contentClick.setStartSpan(startSpan);
		contentClick.setEndSpan(endSpan);
		contentClick.setStringBuilder(stringBuilder);
		contentClick.setMultiActionTextviewClickListener(myMultiActionClickListener);
		contentClick.setOperationType(CONTENT_CLICKED);
		contentClick.setIsUnderLineRequired(isUnderLineRequired);
		contentClick.setDefaultHyperLinkColor(defaultColor);
		contentClick.setPressedHyperLinkColor(pressedColor);
		return contentClick;
	}

	class MyMultiActionClickListener implements
			MultiActionTextviewClickListener {

		@Override
		public void onTextClick(InputObject inputObject) {
			int operation = inputObject.getOperationType();
			String operationType = "";
			switch (operation) {
			case NAME_CLICKED:
				operationType = "Name Clicked";
				break;

			case ACTION_CLICKED:
				operationType = "Action Clicked";
				break;

			case CONTENT_CLICKED:
				operationType = "Content Clicked";
				break;

			}
			Toast.makeText(getApplicationContext(), operationType, Toast.LENGTH_SHORT).show();
		}
	}
}
