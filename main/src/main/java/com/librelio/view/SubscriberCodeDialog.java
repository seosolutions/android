package com.librelio.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.niveales.wind.R;

public class SubscriberCodeDialog {

    private final boolean error;
    private AlertDialogWrapper.Builder builder;
	private Context context;
	private String title;
	
	private OnSubscriberCodeListener onSubscriberCodeListener;
    private EditText subscriberCode;

    public interface OnSubscriberCodeListener {
		void onEnterValue(String value);
        void onCancel();
	}
	
	public SubscriberCodeDialog(Context context, String title, boolean error) {
		this.context = context;
		this.title = title;
        this.error = error;
		configureDialog();
	}
	
	private void configureDialog(){

		builder = new AlertDialogWrapper.Builder(context);
			
		if (null != title){
			builder.setTitle(title);
		}

        View view = LayoutInflater.from(context).inflate(R.layout.subscriber_code_dialog, null, false);
        subscriberCode = (EditText) view.findViewById(R.id.subscriber_code);

        if (error) {
			subscriberCode.setError(context.getString(R.string.incorrect_code));
        }
		
		builder.setView(view);
	
		// Set up the buttons
		builder.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	if (null != onSubscriberCodeListener){
		    		onSubscriberCodeListener.onEnterValue(
		    				subscriberCode.getText().toString().trim());
		    	}
		    }
		});
		
		builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        onSubscriberCodeListener.onCancel();
		    }
		});
	} 
	
	public void setSubscriberCodeListener(OnSubscriberCodeListener onSubscriberCodeListener){
		this.onSubscriberCodeListener = onSubscriberCodeListener;
	}

	public void show(){
		if (null != builder){
			builder.show();
		}
	}
}
