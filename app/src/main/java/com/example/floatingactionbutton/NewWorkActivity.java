package com.example.floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewWorkActivity extends AppCompatActivity {
        EditText editText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newwork);
        editText = findViewById(R.id.etAddNewWork);



    }
    public void btnCreate(View v){
        ContractDatabase contractDatabase = new ContractDatabase(this);
        contractDatabase.openDb();
        contractDatabase.insertListTask(editText.getText().toString());
        contractDatabase.closeDb();
    }

}
