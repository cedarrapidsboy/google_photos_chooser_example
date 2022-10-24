package com.moosedrive.google_photos_chooser_example;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> someActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View fab = findViewById(R.id.button);
        fab.setOnClickListener(v -> buttonClick());

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ChooserActivityResult(this));
    }

    public void buttonClick(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        someActivityResultLauncher.launch(Intent.createChooser(intent, "Select image(s)..."));
    }

    public void addString(String str){
        TextView view = findViewById(R.id.textview);
        String original = (String) view.getText();
        view.setText(str + System.lineSeparator() + original);
    }

    public void clear(){
        TextView view = findViewById(R.id.textview);
        view.setText("");
    }
}