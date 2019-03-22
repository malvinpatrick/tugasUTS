package com.example.acer.latihanuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private RadioGroup rgType;
    private RadioButton  rbTea, tbCoffee, rbSmothies;
    private CheckBox cbPearl, cbPudding, cbRedBean, cbCoconut;
    private Button btnMinus, btnPlus, btnAdd, btnEdit, btnDelete, btnReset;
    private TextView txtQty, txtTotal, txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
