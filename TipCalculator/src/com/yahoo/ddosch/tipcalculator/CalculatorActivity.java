package com.yahoo.ddosch.tipcalculator;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends Activity {

	private double tipPercent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        
        tipPercent = 0.10;
        
        final EditText etSubtotal = (EditText)findViewById(R.id.etSubtotal);
        etSubtotal.addTextChangedListener(getSubtotalChangeWatcher());
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSubtotal, InputMethodManager.SHOW_IMPLICIT);
        
        calculateTip(null);
    }
    
    public void calculateTip(View v) {
    	double subtotal = getSubtotal();
    	tipPercent = getPercentage(v);
    	double tip = subtotal * tipPercent;
    	double total = subtotal + tip;
    	
    	NumberFormat nf = NumberFormat.getCurrencyInstance();
    	
    	final TextView tvTip = (TextView)findViewById(R.id.tvTip);
    	tvTip.setText(nf.format(tip));
    	
    	final TextView tvTotal = (TextView)findViewById(R.id.tvTotal);
    	tvTotal.setText(nf.format(total));
    	
    }
    
    private double getSubtotal() {
    	final EditText etSubtotal = (EditText)findViewById(R.id.etSubtotal);
    	try {
    		return Double.parseDouble(etSubtotal.getText().toString());
    	} catch (NumberFormatException e) {
    		return 0.0;
    	}
    }
    
    private double getPercentage(View v) {
    	if (v == findViewById(R.id.btnTenPercent)) {
    		return 0.10;
    	}
    	if (v == findViewById(R.id.btnFifteenPercent)) {
    		return 0.15;
    	}
    	if (v == findViewById(R.id.btnTwentyPercent)) {
    		return 0.20;
    	}
    	return tipPercent;
    }
    
    private TextWatcher getSubtotalChangeWatcher() {
    	return new TextWatcher() {
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override public void afterTextChanged(Editable s) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				calculateTip(null);
			}
    	};
    }
}
