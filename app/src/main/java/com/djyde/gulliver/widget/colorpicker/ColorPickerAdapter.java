package com.djyde.gulliver.widget.colorpicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djyde.gulliver.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by randy on 15/6/2.
 */
public class ColorPickerAdapter extends RecyclerView.Adapter<ColorPickerAdapter.ViewHolder> {

    private List<ColorPicker.Item> items = new ArrayList<ColorPicker.Item>();
    private Context context;
    private OnPickerSelectedListener onPickerSelectedListener;

    public interface OnPickerSelectedListener{
        void onSelected(ColorPicker.Item selectedItem);
    }

    public void setOnPickerSelectedListener(OnPickerSelectedListener listener){
        this.onPickerSelectedListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView picker;
        public ViewHolder(View itemView) {
            super(itemView);
            picker = (TextView)itemView.findViewById(R.id.picker);
        }
    }

    public ColorPickerAdapter(Context context, List<ColorPicker.Item> items) {
        super();
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_color_picker,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPickerSelectedListener.onSelected(items.get(position));
            }
        });
        holder.picker.setBackgroundColor(items.get(position).getColor());
    }
}
