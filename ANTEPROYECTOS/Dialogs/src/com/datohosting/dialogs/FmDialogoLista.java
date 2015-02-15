package com.datohosting.dialogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class FmDialogoLista extends DialogFragment {
	
	
	private int num = 0;
	private static int single = 0;
	boolean[] multi;  
	
	private static final String FILE_NAME = "multi.txt";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    num = getArguments().getInt("num");
	}
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle(R.string.pick_single);
		
		final String[] colores = getResources().getStringArray(R.array.colors_array);
		
		if (num == 1) {
			DialogoLista(builder, colores);
		} else if (num == 2) {
			DialogoSingleChoice(builder, colores);
		} else if (num == 3) {
			DialogoMultiChoice(builder, colores);
		}
        
        return builder.create();
	}
	
	
	
	
	
	private void DialogoLista(AlertDialog.Builder builder, final String[] colores) {
        builder.setItems(colores, new DialogInterface.OnClickListener() {
        	@Override
        	public void onClick(DialogInterface dialog, int which) {
        		Toast.makeText(getActivity(), colores[which], Toast.LENGTH_SHORT).show();
        	}
        });
	}
	
	
	private void DialogoSingleChoice(AlertDialog.Builder builder, final String[] colores) {
		builder.setSingleChoiceItems(colores, single, new DialogInterface.OnClickListener() {
			@Override
        	public void onClick(DialogInterface dialog, int which) {
        		Toast.makeText(getActivity(), colores[single = which], Toast.LENGTH_SHORT).show();
        		dialog.dismiss();
        	}
        });
	}
	
	
	private void DialogoMultiChoice(AlertDialog.Builder builder, final String[] colores) {
		
		multi = new boolean[colores.length];
		loadItems(multi);
		
		builder.setTitle(R.string.pick_multi);	

		builder.setMultiChoiceItems(colores, multi, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				
			}
		});
		
		builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id) {
            	 saveItems(multi);
             }
         });
         
         builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id) {
            	 
             }
         });
	}
	
	
	
	
	
	private void saveItems(boolean[] multi) {
		PrintWriter writer = null;
		try {
			FileOutputStream fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)));

			for (int i = 0; i < multi.length; i++) {
				writer.println(Boolean.toString(multi[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}
	
	
	private void loadItems(boolean[] multi) {
		BufferedReader reader = null;
		try {
			FileInputStream fis = getActivity().openFileInput(FILE_NAME);
			reader = new BufferedReader(new InputStreamReader(fis));
			
			String title = null;
			int i = 0;
			
			while ((title = reader.readLine()) != null) {
				if (title.equals("true")) {
					multi[i] = true;
				} else if (title.equals("false")) {
					multi[i] = false;
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}