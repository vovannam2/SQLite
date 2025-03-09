package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(Context context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size(); // Trả về số lượng phần tử trong danh sách
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position); // Trả về phần tử tại vị trí position
    }

   /* @Override
    public long getItemId(int position) {
        return noteList.get(position).getIdNode(); // Trả về ID của phần tử
    }*/
    @Override
    public long getItemId(int i) {
        return i; // Trả về ID của item, có thể dùng i làm ID nếu không có ID riêng
    }
    /*@Override
    public Object getItem(int i) {
        return listMonHoc.get(i); // Trả về đối tượng MonHoc tại vị trí i
    }*/
    private static class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.textViewNote = convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDelete = convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = convertView.findViewById(R.id.imageViewEdit);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy dữ liệu và gán vào View
        NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());

        return convertView;
    }
}
