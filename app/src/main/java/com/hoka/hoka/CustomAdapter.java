package com.hoka.hoka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    public class CustomAdapter extends BaseExpandableListAdapter {
        private LayoutInflater inflater;
        // Набор данных (теперь типа ContactGroup, а не ContactItem)
        private ArrayList<ContactGroup> data;
        public CustomAdapter(Context context, ArrayList<ContactGroup> data) {
            inflater = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            this.data = data;
        }
        @Override
        public ContactItem getChild(int groupPosition, int childPosition) {
            return data.get(groupPosition).getContactItem(childPosition);
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return data.get(groupPosition).getContactsList().size();
        }
        @Override
        public ContactGroup getGroup(int groupPosition) {
            return data.get(groupPosition);
        }
        @Override
        public int getGroupCount() {
            return data.size();
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
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                view = inflater.inflate(R.layout.row_group, null);
            }
            ContactGroup group = data.get(groupPosition);
            TextView name = (TextView)view.findViewById(R.id.sname);
            name.setText(group.getName());
            return view;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                view = inflater.inflate(R.layout.row_childs, null);
            }
            ContactItem item =
                    data.get(groupPosition).getContactItem(childPosition);
            ImageView image = (ImageView)view.findViewById(R.id.image);
            TextView name = (TextView)view.findViewById(R.id.name);
            TextView phone = (TextView)view.findViewById(R.id.phone);

            if(item.getStatus()==0){
                image.setImageResource(R.drawable.a1);
            }
            if(item.getStatus()==1){
                image.setImageResource(R.drawable.a2);
            }
            if(item.getStatus()==2){
                image.setImageResource(R.drawable.a3);
            }

            //image.setImageResource(item.getPhotoID());
            name.setText(item.getName());
            phone.setText(item.getPhone());
            return view;
        }
    }

