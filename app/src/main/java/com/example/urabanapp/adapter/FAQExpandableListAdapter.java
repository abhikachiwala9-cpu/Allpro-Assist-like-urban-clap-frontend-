package com.example.urabanapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.urabanapp.R;
import com.example.urabanapp.model.FAQModel;

import java.util.List;

public class FAQExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<FAQModel> faqs;

    public FAQExpandableListAdapter(Context context, List<FAQModel> faqs) {
        this.context = context;
        this.faqs = faqs;
    }
    @Override
    public int getGroupCount() {
        return faqs != null ? faqs.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return faqs.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return faqs.get(groupPosition).getAnswer();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent,false);
        }

        TextView questionText = (TextView) convertView.findViewById(android.R.id.text1);
        questionText.setText(faqs.get(groupPosition).getQuestion());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_faq_ans, parent,false);
        }

        TextView answerText = (TextView) convertView.findViewById(R.id.childText);
        answerText.setText(faqs.get(groupPosition).getAnswer());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
