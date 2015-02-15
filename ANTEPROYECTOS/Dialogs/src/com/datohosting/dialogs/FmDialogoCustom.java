package com.datohosting.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FmDialogoCustom extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setTitle("SIGN IN");
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_custom, null);

        final EditText username = (EditText) view.findViewById(R.id.username);
        final EditText password = (EditText) view.findViewById(R.id.password);

        builder.setView(view);
        
        builder.setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {     
        	@Override  
        	public void onClick(DialogInterface dialog, int id) {
        		
        		String resultado = "Usuario: " + username.getText().toString() + "\n" +
        				"Clave: " + password.getText().toString();
        		
        		Toast.makeText(getActivity(), resultado, Toast.LENGTH_SHORT).show();
        	}
        });
        
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
        	@Override  
        	public void onClick(DialogInterface dialog, int id) {
        		
        	}
        });    
        
        return builder.create();
	}
	
}