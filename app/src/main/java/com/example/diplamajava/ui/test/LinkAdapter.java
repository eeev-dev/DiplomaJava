package com.example.diplamajava.ui.test;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_link, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.titleTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String[] test = testList.get(position);
        holder.title.setText(test[0]);

        holder.title.setOnClickListener(v -> {
            String url = test[1];
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        });

        holder.title.setOnLongClickListener(v -> {
            String url = test[1];

            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("URL", url);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(context, "Ссылка скопирована", Toast.LENGTH_SHORT).show();
            return true; // важно вернуть true, чтобы событие long click не продолжалось как обычный click
        });


        return convertView;
    }
}