package com.example.sqlite;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, name, address;
    Button insert, delete, update, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        insert = findViewById(R.id.btninsert);
        delete = findViewById(R.id.btndelete);
        update = findViewById(R.id.btnupdate);
        view = findViewById(R.id.btnview);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idtext = id.getText().toString();
                String nametext = name.getText().toString();
                String addresstext = address.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(idtext, nametext, addresstext);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Data was not inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idtext = id.getText().toString();
                String nametext = name.getText().toString();
                String addresstext = address.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(idtext, nametext, addresstext);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idtext = id.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(idtext);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Student ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Address :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Record");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}
