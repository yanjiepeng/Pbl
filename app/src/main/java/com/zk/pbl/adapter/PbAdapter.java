package com.zk.pbl.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zk.pbl.bean.PbInfo;
import com.zk.pbl.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class PbAdapter extends BaseAdapter {

    Context mContext;
    List<PbInfo> data;
    LayoutInflater inflater;
    public PbAdapter(Context context , List<PbInfo> data) {
        this.mContext = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView ==  null) {
            view = inflater.inflate(R.layout.adapter_pb , null);
            TextView tv_pb_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_pb_line = (TextView) view.findViewById(R.id.tv_line);
            TextView tv_pb_status = (TextView) view.findViewById(R.id.tv_status);
            TextView tv_pb_create_time = (TextView) view.findViewById(R.id.tv_create_time);
            TextView tv_pb_order = (TextView) view.findViewById(R.id.tv_order);
            TextView tv_pb_type = (TextView) view.findViewById(R.id.tv_type);
            TextView tv_pb_emergency = (TextView) view.findViewById(R.id.tv_emergency);
            TextView tv_pb_amount = (TextView) view.findViewById(R.id.tv_amount);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tv_pb_id = tv_pb_id;
            viewHolder.tv_pb_line = tv_pb_line;
            viewHolder.tv_pb_status = tv_pb_status;
            viewHolder.tv_pb_create_time = tv_pb_create_time;
            viewHolder.tv_pb_order = tv_pb_order;
            viewHolder.tv_pb_type = tv_pb_type;
            viewHolder.tv_pb_emergency = tv_pb_emergency;
            viewHolder.tv_pb_amount = tv_pb_amount;

            view.setTag(viewHolder);
        }else {
            view = convertView;
            Log.v("TaskAdapter适配器", "复用");
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        //此处进行数据适配



        return view;
    }

    class ViewHolder {
        TextView tv_pb_id;
        TextView tv_pb_line;
        TextView tv_pb_status;
        TextView tv_pb_create_time;
        TextView tv_pb_order;
        TextView tv_pb_type;
        TextView tv_pb_emergency;
        TextView tv_pb_amount;
    }
}
