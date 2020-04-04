package pl.rapid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import pl.rapid.R;

public class NewEntry extends AppCompatActivity {

    private ArrayAdapter species;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        species = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, new String[] {"Pies", "Kot", "Rybki"});
        Spinner spinner = findViewById(R.id.speciesSpinner);
        spinner.setAdapter(species);
    }

    public void send(View view) {
        Animal animal = new Animal(
                ((Spinner) findViewById(R.id.speciesSpinner)).getSelectedItem().toString(),
                ((EditText) findViewById(R.id.colorText)).getText().toString(),
                Float.parseFloat(((EditText) findViewById(R.id.sizeText)).getText().toString()),
                ((EditText) findViewById(R.id.descriptionText)).getText().toString()
        );

        Intent intention = new Intent();
        intention.putExtra("entry", animal);
        setResult(RESULT_OK, intention);
        finish();
    }
}
