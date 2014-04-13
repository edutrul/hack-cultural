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
import com.hackatonlima.cultuhacklima.bean.EventoLocalBean;

public class EventosLocalesBaseAdapter extends BaseAdapter {

	Context context;
	List<EventoLocalBean> eventosLocales;

	public EventosLocalesBaseAdapter(Context context, List<EventoLocalBean> eventosLocales) {
		this.context = context;
		this.eventosLocales = eventosLocales;
	}

	/* private view holder class */
	private class ViewHolder {
		ImageView imageView;
		TextView txtNombre;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.layout_eventos_locales, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.img_evento_local);
			holder.txtNombre = (TextView) convertView
					.findViewById(R.id.nombre_evento_local);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		EventoLocalBean eventoLocal = (EventoLocalBean) getItem(position);

		holder.imageView.setImageResource(eventoLocal.getImagen());// foto
		
		holder.txtNombre.setText(eventoLocal.getNombre());

		return convertView;
	}

	@Override
	public int getCount() {
		return eventosLocales.size();
	}

	@Override
	public Object getItem(int position) {
		return eventosLocales.get(position);
	}

	@Override
	public long getItemId(int position) {
		return eventosLocales.indexOf(getItem(position));
	}

}
