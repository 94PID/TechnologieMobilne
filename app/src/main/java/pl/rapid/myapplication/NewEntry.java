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

    private int modyfi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        species = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, new String[] {"Pies", "Kot", "Rybki"});
        Spinner spinner = findViewById(R.id.speciesSpinner);
        spinner.setAdapter(species);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras.getSerializable("element") != null) {
                Animal animal = (Animal) extras.getSerializable("element");

                ((EditText) findViewById(R.id.colorText)).setText(animal.getColor());
                ((EditText) findViewById(R.id.descriptionText)).setText(animal.getDescription());
                ((EditText) findViewById(R.id.sizeText)).setText(Float.toString(animal.getSize()));
                this.modyfi_id = animal.getId();
            }
        } catch (Exception ex) {
            this.modyfi_id = 0;
        }

    }

    public void send(View view) {
        Animal animal = new Animal(
                ((Spinner) findViewById(R.id.speciesSpinner)).getSelectedItem().toString(),
                ((EditText) findViewById(R.id.colorText)).getText().toString(),
                Float.parseFloat(((EditText) findViewById(R.id.sizeText)).getText().toString()),
                ((EditText) findViewById(R.id.descriptionText)).getText().toString()
        );
        animal.setId(this.modyfi_id);

        Intent intention = new Intent();
        intention.putExtra("entry", animal);
        setResult(RESULT_OK, intention);
        finish();
    }
}
