package com.zk.pbl.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.zk.pbl.R;
import com.zk.pbl.bean.PbInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class PbExpandableAdapter implements ExpandableListAdapter {

    Context context;
    List<PbInfo> data ;
    LayoutInflater inflater;
    public PbExpandableAdapter(Context context , List<PbInfo> data ) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = null ;

        if (convertView == null) {
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
            view = convertView ;
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        /**
         * 此处将主订单适配到UI
         */
        viewHolder.tv_pb_id.setText(data.get(groupPosition).getId()+"");
        viewHolder.tv_pb_line.setText(data.get(groupPosition).getProdline()+"");
        viewHolder.tv_pb_status.setText(data.get(groupPosition).getState()+"");
        viewHolder.tv_pb_create_time.setText(data.get(groupPosition).getCreatedate()+"");
        viewHolder.tv_pb_order.setText(data.get(groupPosition).getSeq()+"");
        viewHolder.tv_pb_type.setText(data.get(groupPosition).getType()+"");
        viewHolder.tv_pb_emergency.setText(data.get(groupPosition).getEmergency()+"");
        viewHolder.tv_pb_amount.setText(data.get(groupPosition).getNumber()+"");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view = null ;

        if (convertView == null) {
            view = inflater.inflate(R.layout.adapter_pb , null);

            TextView tv_pb_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_pb_line = (TextView) view.findViewById(R.id.tv_line);
            TextView tv_pb_status = (TextView) view.findViewById(R.id.tv_status);
            TextView tv_pb_create_time = (TextView) view.findViewById(R.id.tv_create_time);
            TextView tv_pb_order = (TextView) view.findViewById(R.id.tv_order);
            TextView tv_pb_type = (TextView) view.findViewById(R.id.tv_type);
            TextView tv_pb_emergency = (TextView) view.findViewById(R.id.tv_emergency);
            TextView tv_pb_amount = (TextView) view.findViewById(R.id.tv_amount);

            ViewHolderChild viewHolder = new ViewHolderChild();
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
            view = convertView ;
        }
        ViewHolderChild viewHolder = (ViewHolderChild) view.getTag();
        /**
         * 此处将子订单适配到UI
         */

        viewHolder.tv_pb_id.setText(data.get(groupPosition).getChildren().get(childPosition).getId()+"");
        viewHolder.tv_pb_line.setText(data.get(groupPosition).getChildren().get(childPosition).getProdline()+"");
        viewHolder.tv_pb_status.setText(data.get(groupPosition).getChildren().get(childPosition).getState()+"");
        viewHolder.tv_pb_create_time.setText(data.get(groupPosition).getChildren().get(childPosition).getCreatedate()+"");
        viewHolder.tv_pb_order.setText(data.get(groupPosition).getChildren().get(childPosition).getSeq()+"");
        viewHolder.tv_pb_emergency.setText(data.get(groupPosition).getEmergency()+"");
        viewHolder.tv_pb_amount.setText(data.get(groupPosition).getChildren().get(childPosition).getNumber()+"");

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
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
    class ViewHolderChild {
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
