package com.datohosting.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class FmDialogoBasico extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setTitle("Titulo");
        
        builder.setIcon(R.drawable.ic_launcher);
        
        builder.setMessage("Area de contenido");
        
        builder.setPositiveButton(R.string.positivo, new DialogInterface.OnClickListener() {
        	@Override
        	public void onClick(DialogInterface dialog, int id) {
            	
            }
        });
        
        builder.setNeutralButton(R.string.neutral, new DialogInterface.OnClickListener() {
        	@Override
        	public void onClick(DialogInterface dialog, int id) {
            	
            }
        });
        
        builder.setNegativeButton(R.string.negativo, new DialogInterface.OnClickListener() {
        	@Override
        	public void onClick(DialogInterface dialog, int id) {
        		
        	}
        });
        
        return builder.create();
	}
	
}