package pl.rapid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pl.rapid.R;

public class NewEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
    }

    public void send(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intention = new Intent();
        intention.putExtra("entry", editText.getText().toString());
        setResult(RESULT_OK, intention);
        finish();
    }
}
