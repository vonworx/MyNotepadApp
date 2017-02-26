package com.projectbasis.sample.mynotepadapp;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends Activity {

    Button newButton,saveButton,openButton;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create textfield
        text        = (EditText)findViewById(R.id.text);

        //Create Elements
        openButton  =(Button)findViewById(R.id.openBtn);
        newButton   =(Button)findViewById(R.id.newBtn);
        saveButton  =(Button)findViewById(R.id.saveBtn);


    }

    public void buttonAction(View v) {
        final EditText fileName         = new EditText(this);
        AlertDialog.Builder alertDiag   = new AlertDialog.Builder(this);

        alertDiag.setView(fileName);

        if (v.getId() == R.id.saveBtn) {
            alertDiag.setMessage("Save File");

            alertDiag.setPositiveButton("Save",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        FileOutputStream fout=openFileOutput(fileName.getText().toString()+".txt",MODE_WORLD_READABLE);
                        fout.write(text.getText().toString().getBytes());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Occured: "+e,Toast.LENGTH_LONG).show();
                    }
                }
            });

            alertDiag.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDiag.show();
        }


        if(v.getId()==R.id.openBtn) {
            alertDiag.setMessage("Open File");
            alertDiag.setPositiveButton("Open",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int f;
                    text.setText("");
                    try {
                        FileInputStream fin = openFileInput(fileName.getText().toString()+".txt");

                        while ((f = fin.read()) != -1)
                        {
                            text.setText((text.getText().toString() + Character.toString((char) f)));
                        }
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Occured: "+e,Toast.LENGTH_LONG).show();
                    }
                }
            });

            alertDiag.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDiag.show();
        }

        if(v.getId()==R.id.newBtn) {
            text.setText("");
        }
    }
}
