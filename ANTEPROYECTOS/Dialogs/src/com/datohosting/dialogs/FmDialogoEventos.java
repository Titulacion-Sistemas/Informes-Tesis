package com.datohosting.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class FmDialogoEventos extends DialogFragment {
	
	
	public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    
	DialogListener mListener;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " debe implementar DialogListener");
        }
    }
	
	
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setTitle("Titulo");
        
        builder.setIcon(R.drawable.ic_launcher);
        
        builder.setMessage("Area de contenido");
        
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
        	@Override
        	public void onClick(DialogInterface dialog, int id) {
        		mListener.onDialogPositiveClick(FmDialogoEventos.this);
            }
        });
        
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
        	@Override
        	public void onClick(DialogInterface dialog, int id) {
        		mListener.onDialogNegativeClick(FmDialogoEventos.this);
        	}
        });
        
        return builder.create();
	}
	
}