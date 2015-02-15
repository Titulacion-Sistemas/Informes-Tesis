package com.datohosting.dialogs;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.datohosting.dialogs.FmDialogoEventos.DialogListener;

public class MenuPrincipal extends FragmentActivity implements OnItemClickListener, DialogListener {
	
	private ArrayList<ObjetoEntradas> items = new ArrayList<ObjetoEntradas>();
	private boolean mIsLargeLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_principal);
		
		mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
		
		crearEntradas();  
	    EntradasAdapter adapter = new EntradasAdapter(this, items);
	    
	    ListView lv = (ListView)findViewById(R.id.list);
	    lv.setAdapter(adapter);   
	    lv.setOnItemClickListener(this);
	}
	
	

	private void crearEntradas() {
	    items.add(new ObjetoEntradas(1, "1. Dialogo Basico", "Ejemplo de un dialogo"));
	    items.add(new ObjetoEntradas(2, "2. Dialogo Lista I", "Lista con una sola opcion"));
	    items.add(new ObjetoEntradas(3, "3. Dialogo Lista II", "Lista con una opcion persistente"));
	    items.add(new ObjetoEntradas(4, "4. Dialogo Lista III", "Lista con varias opciones persistentes"));
	    items.add(new ObjetoEntradas(5, "5. Dialogo Custom", "Como crear un dialogo personalizado"));
	    items.add(new ObjetoEntradas(6, "6. Devolver eventos", "Como compartir eventos del dialogo"));
	    items.add(new ObjetoEntradas(7, "7. Dialogo Pantalla Completa", ""));
	}
	
	
	
	
	
/** INTERFACE DIALOG LISTENER **/
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Toast.makeText(MenuPrincipal.this, "ACEPTAR", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		Toast.makeText(MenuPrincipal.this, "CANCELAR", Toast.LENGTH_SHORT).show();
	}
	
	
	
	
	
/** INTERFACE ON ITEM CLICK LISTENER **/
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		switch (arg2) {
			case 0:
				new FmDialogoBasico().show(getSupportFragmentManager(), "DialogoBasico");
				break;
			case 1:
			case 2:
			case 3:
				FmDialogoLista lista = new FmDialogoLista();
				
				Bundle args = new Bundle();
				args.putInt("num", arg2);
			    
			    lista.setArguments(args);
			    
			    lista.show(getSupportFragmentManager(), "DialogoLista");
				break;
			case 4:
				new FmDialogoCustom().show(getSupportFragmentManager(), "DialogoCustom");
				break;
			case 5:
				new FmDialogoEventos().show(getSupportFragmentManager(), "DialogoBasico");
				break;
			case 6:
				showDialog();
				break;
		}
	}
	
	private void showDialog() {
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    FmDialogoFullScreen newFragment = new FmDialogoFullScreen();
	    
	    if (mIsLargeLayout) {
	        newFragment.show(fragmentManager, "dialog");
	    } else {
	        FragmentTransaction transaction = fragmentManager.beginTransaction();
	        
	        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	        
	        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
	    }
	}
	
	
	
	
	
/** OBJETO PARA CADA ITEM DEL LIST VIEW **/
	
	public class ObjetoEntradas {
		
		private int id;
		private String titulo;
		private String descripcion;
		
		public ObjetoEntradas(int id, String titulo, String descripcion) {
			this.id = id;
			this.titulo = titulo;
			this.descripcion = descripcion;
		}
		
		public int getId() {return id;}
		public String getTitulo(){return titulo;}
		public String getDescripcion(){return descripcion;}
	}
	
	
	
	
	
/** ADAPTADOR PARA EL LISTVIEW DEL MENU PRINCIPAL **/
	
	public class EntradasAdapter extends BaseAdapter {
		
		protected Activity activity;
		protected ArrayList<ObjetoEntradas> items;
		
		public EntradasAdapter(Activity activity, ArrayList<ObjetoEntradas> items) {
			this.activity = activity;
			this.items = items;
		}
		
		public int getCount() {return items.size();}
		public Object getItem(int position) {return items.get(position);}
		public long getItemId(int position) {return items.get(position).getId();}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			
	        if(vi == null) {
	        	LayoutInflater inflater = activity.getLayoutInflater();
	        	vi = inflater.inflate(R.layout.plantilla_listview, null);
	        }
	        
	        ObjetoEntradas item = items.get(position);
	             
	        TextView titulo = (TextView) vi.findViewById(R.id.titulo);
	        titulo.setText(item.getTitulo());
	             
	        TextView descripcion = (TextView) vi.findViewById(R.id.descripcion);
	        descripcion.setText(item.getDescripcion());
	     
	        return vi;
		}
	}
	
	
	
	
	
}