package pl.rapid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import pl.rapid.R;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;
    private SimpleCursorAdapter adapter;
    MySQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MySQLite(this);

        target = new ArrayList<>();
        String[] values = new String[]{
                "Pies", "Kot", "Koń", "Gołąb", "Kruk", "Dzik", "Karp", "Osioł", "Chomik", "Mysz",
                "Jeż", "Karaluch"
        };

        target.addAll(Arrays.asList(values));
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                db.list(), new String[]{"_id", "species"},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(this.adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) view.findViewById(android.R.id.text1);
                Animal animal = db.getAnimal(Integer.parseInt(name.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), NewEntry.class);
                intent.putExtra("element", animal);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Animal newEntry = (Animal) extras.get("entry");
            this.db.add(newEntry);
            adapter.changeCursor(db.list());
            adapter.notifyDataSetChanged();
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Animal newEntry = (Animal) extras.get("entry");
            this.db.update(newEntry);
            adapter.changeCursor(db.list());
            adapter.notifyDataSetChanged();
        }
    }

    public void onNewEntry(MenuItem mi) {
        Intent intention = new Intent(this, NewEntry.class);
        startActivityForResult(intention, 1);
    }

}
