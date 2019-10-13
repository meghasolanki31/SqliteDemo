package com.example.sqllitedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sqllitedatabase.Database.DatabaseHelper;
import com.example.sqllitedatabase.adepters.CustomAdpeter;
import com.example.sqllitedatabase.model.UserModel;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper databaseHelper;
    UserModel userModel;
    ArrayList<UserModel> arrayList;
    int item_id;
    CustomAdpeter customAdpeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView = findViewById(R.id.CusList);
        databaseHelper = new DatabaseHelper(ShowDataActivity.this);
        arrayList = new ArrayList<>();
        arrayList.addAll(databaseHelper.getAllUser());

        customAdpeter = new CustomAdpeter(ShowDataActivity.this, arrayList);
        listView.setAdapter(customAdpeter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);

                item_id = cursor.getInt(cursor.getColumnIndex(userModel.KEY_USER_ID));
            }


        });

        registerForContextMenu(listView);

        //  databaseHelper.deleteNote(1);

      /*  userModel = new UserModel();

        userModel = arrayList.get(1);
*/
        //  userModel.number = "0000010000";

        //  databaseHelper.updateNote(userModel);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                userModel=new UserModel();
                userModel=arrayList.get(item_id);
              //  databaseHelper.DeleteById(userModel.id);
                //databaseHelper.DeleteById(item_id);
                databaseHelper.deleteNote(userModel.id);
                customAdpeter.notifyDataSetChanged();
                return true;
            case R.id.update:
                userModel = new UserModel();
                userModel = arrayList.get(1);

                userModel.number = "0000010000";

                databaseHelper.updateNote(userModel);
                customAdpeter.notifyDataSetChanged();

        }
        return super.onContextItemSelected(item);
    }
}
