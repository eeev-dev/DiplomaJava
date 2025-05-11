package com.example.diplamajava.ui.test.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diplamajava.R;

import java.util.List;

public class LinkAdapter extends BaseAdapter {

    private final Context context;
    private final List<String[]> testList;
    private final LayoutInflater inflater;

    public LinkAdapter(Context context, List<String[]> testList) {
        this.context = context;
        this.testList = testList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return testList.size();
    }

    @Override
    public Object getItem(int position) {
        return testList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView title;
        TextView link;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_link, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.titleTextView);
            holder.link = convertView.findViewById(R.id.linkTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String[] test = testList.get(position);
        holder.title.setText(test[0]);
        holder.link.setText(test[1]);

        holder.link.setOnClickListener(v -> {
            String url = test[1];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        });

        return convertView;
    }
}