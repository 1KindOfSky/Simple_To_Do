package sg.edu.rp.c346.id20029443.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd, btnClear, btnDelete;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;
    ListView lvTask;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        lvTask = findViewById(R.id.lvTask);
        spinner = findViewById(R.id.spinner);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etElement.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etElement.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, task + " Has Been Added", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(alTask.size() == 0) {
                    Toast.makeText(MainActivity.this, "There is no task to remove", Toast.LENGTH_LONG).show();
                    return;
                }
                int pos = Integer.parseInt(etElement.getText().toString());

                if (pos > alTask.size()-1) {
                    Toast.makeText(MainActivity.this, "Index Position Is Too Big", Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Task Has Been Removed", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "All Task Have Been Cleared" , Toast.LENGTH_LONG).show();
            }
        });



    }
}