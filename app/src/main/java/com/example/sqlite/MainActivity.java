package com.example.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo database
        InitDatabaseSQLite();

        // Kiểm tra xem đã có dữ liệu trong bảng chưa, nếu chưa thì mới thêm
        if (!checkDataExists()) {
            createDatabaseSQLite();
        }

        // Ánh xạ ListView
        listView = findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(MainActivity.this, R.layout.custom_row, arrayList);
        listView.setAdapter(adapter);

        // Lấy dữ liệu từ database và cập nhật ListView
        loadDataFromSQLite();
    }

    private void InitDatabaseSQLite() {
        databaseHandler = new DatabaseHandler(this, "notes.sqlite", null, 1);
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    private void createDatabaseSQLite() {
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 2')");
    }

    private boolean checkDataExists() {
        Cursor cursor = databaseHandler.GetData("SELECT COUNT(*) FROM Notes");
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0; // Nếu có dữ liệu thì return true
        }
        return false;
    }

    private void loadDataFromSQLite() {
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        arrayList.clear(); // Xóa dữ liệu cũ để tránh trùng lặp

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int id = cursor.getInt(0);
            arrayList.add(new NotesModel(name, id));
        }
        cursor.close();

        // Cập nhật ListView
        adapter.notifyDataSetChanged();
    }
}
