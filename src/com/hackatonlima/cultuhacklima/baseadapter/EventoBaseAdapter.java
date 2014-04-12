package com.hackatonlima.cultuhacklima.baseadapter;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.bean.EventoBean;


public class EventoBaseAdapter extends BaseAdapter {
	
	
	Context context;
	List<EventoBean> eventos;

	public EventoBaseAdapter(Context context, List<EventoBean> eventos) {
		this.context = context;
		this.eventos = eventos;
	}

	/* private view holder class */
	private class ViewHolder {
		ImageView imageView;
		TextView txtNombre;
		TextView txtFecha;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.layout_eventos, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.evento_imagen);
			holder.txtNombre = (TextView) convertView.findViewById(R.id.txt_nombre_evento);
			holder.txtFecha = (TextView) convertView.findViewById(R.id.txt_fecha_hora_evento);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		EventoBean eventoBean = (EventoBean) getItem(position);
		
		holder.imageView.setImageResource(eventoBean.getImagen());
		holder.txtNombre.setText(eventoBean.getNombre());
		holder.txtFecha.setText(eventoBean.getFecha());

		return convertView;
	}

	@Override
	public int getCount() {
		return eventos.size();
	}

	@Override
	public Object getItem(int position) {
		return eventos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return eventos.indexOf(getItem(position));
	}

}
