package com.example.nacho.livedataexample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch simpleSwitch, simpleSwitch1;
    TextView txtTrabajo, txtApruebo, txtEdad;
    EditText etEdad;

    private final MutableLiveData<Boolean> mMutableLiveDataFirst = new MutableLiveData<>();
    private final MutableLiveData<Boolean> MutableLiveDataSecond = new MutableLiveData<>();
    private final MutableLiveData<String> mMutableLiveDataThird = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        simpleSwitch1 = (Switch) findViewById(R.id.simpleSwitch1);
        txtTrabajo = (TextView) findViewById(R.id.txtTrabajo);
        txtApruebo = (TextView) findViewById(R.id.txtApruebo);
        txtEdad = (TextView) findViewById(R.id.txtEdad);
        etEdad = (EditText) findViewById(R.id.etEdad);




      mMutableLiveDataFirst.observe(this, new Observer<Boolean>() {
                    @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                        txtTrabajo.setText("Trabajas en casa? ");
                        if (aBoolean == true){
                            txtTrabajo.setText(txtTrabajo.getText() +"Si");

                        } else {
                            txtTrabajo.setText(txtTrabajo.getText()+"No");

                        }


            }
        });

        MutableLiveDataSecond.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                txtApruebo.setText("Vas a aprobar? ");
                if (aBoolean == true){

                    txtApruebo.setText(txtApruebo.getText() + "Si");
                } else {

                    txtApruebo.setText(txtApruebo.getText() + "No");
                }


            }
        });

        mMutableLiveDataThird.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                txtEdad.setText("Tienes " + s +" años.");

            }
        });


        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
            if (isChecked == true){
                mMutableLiveDataFirst.postValue(true);
            }else {
                mMutableLiveDataFirst.postValue(false);
            }
            }
        });

        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked == true){
                    MutableLiveDataSecond.postValue(true);
                }else {
                    MutableLiveDataSecond.postValue(false);
                }
            }
        });


        etEdad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mMutableLiveDataThird.postValue(etEdad.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMutableLiveDataThird.postValue(etEdad.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                mMutableLiveDataThird.postValue(etEdad.getText().toString());
            }
        });

    }




    }


