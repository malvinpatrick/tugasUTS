package com.example.acer.latihanuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private RadioGroup rgType;
    private RadioButton  rbTea, rbCoffee, rbSmoothies;
    private CheckBox cbPearl, cbPudding, cbRedBean, cbCoconut;
    private Button btnMinus, btnPlus, btnAdd, btnDelete, btnReset;
    private TextView txtQty, txtInfo;
    private RecyclerView rvOrder;
    private OrderAdapter orderAdapter;

    private ArrayList<Order> orderList = new ArrayList<>();
    private long total = 0;
    private int indexClick = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.editText_name);
        rgType = findViewById(R.id.radioGroup_type);
        rbTea = findViewById(R.id.radioButton_tea);
        rbCoffee = findViewById(R.id.radioButton_coffee);
        rbSmoothies = findViewById(R.id.radioButton_smoothies);
        cbPearl = findViewById(R.id.cbPearl);
        cbPudding = findViewById(R.id.cbPudding);
        cbRedBean = findViewById(R.id.cbRedBean);
        cbCoconut = findViewById(R.id.cbCoconut);
        btnMinus = findViewById(R.id.button_minus);
        btnPlus = findViewById(R.id.button_plus);
        txtQty = findViewById(R.id.textView_qty);
        btnAdd = findViewById(R.id.button_add);
        btnDelete = findViewById(R.id.button_delete);
        btnReset = findViewById(R.id.button_reset);
        rvOrder = findViewById(R.id.recyclerview_order);
        txtInfo = findViewById(R.id.textView_info);

        //ORDER ADAPTER CLICK
        orderAdapter = new OrderAdapter(orderList, new ClickListener() {
            @Override
            public void recycleViewClick(View v, int index) {
                indexClick = index;
                cbCoconut.setChecked(false);
                cbRedBean.setChecked(false);
                cbPudding.setChecked(false);
                cbPearl.setChecked(false);

                //TYPE
                if (orderList.get(index).getType().equals("Tea")) rbTea.setChecked(true);
                else if (orderList.get(index).getType().equals("Coffee")) rbCoffee.setChecked(true);
                else rbSmoothies.setChecked(true);

                //TOPPINGS
                ArrayList<String> temp = orderList.get(index).getToppings();
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).equals("Pearl")) cbPearl.setChecked(true);
                    else if (temp.get(i).equals("Pudding")) cbPudding.setChecked(true);
                    else if (temp.get(i).equals("Pudding")) cbPudding.setChecked(true);
                    else if (temp.get(i).equals("Red Bean")) cbRedBean.setChecked(true);
                    else if (temp.get(i).equals("Coconut Jelly")) cbCoconut.setChecked(true);
                }
            }
        });

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rvOrder.setLayoutManager(lm);
        rvOrder.setAdapter(orderAdapter);
        total = 0;

        //BUTTON MINUS
        btnMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int temp = Integer.parseInt(txtQty.getText().toString()) - 1;
                if(temp >= 1) txtQty.setText(String.valueOf(temp));
            }
        });

        //BUTTON PLUS
        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int temp = Integer.parseInt(txtQty.getText().toString()) + 1;
                txtQty.setText(String.valueOf(temp));
            }
        });

        //BUTTON ADD
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(edName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Field Name cannot be empty",Toast.LENGTH_SHORT).show();
                }else{
                    txtInfo.setText("Hi, " + edName.getText().toString());
                    int temp_subtotal = 0;
                    String temp_tipe = "";
                    ArrayList<String> arrTopping = new ArrayList<>();
                    if(rbTea.isChecked()){
                        temp_subtotal += 23000;
                        temp_tipe = "Tea";
                    }else if(rbCoffee.isChecked()){
                        temp_subtotal += 25000;
                        temp_tipe = "Coffee";
                    }else{
                        temp_subtotal += 30000;
                        temp_tipe = "Smoothies";
                    }

                    //TOPPINGS
                    if(cbPearl.isChecked()){
                        temp_subtotal += 3000;
                        arrTopping.add("Pearl");
                    }
                    if(cbPudding.isChecked()){
                        temp_subtotal += 3000;
                        arrTopping.add("Pudding");
                    }
                    if(cbRedBean.isChecked()){
                        temp_subtotal += 4000;
                        arrTopping.add("Red Bean");
                    }
                    if(cbCoconut.isChecked()){
                        temp_subtotal += 4000;
                        arrTopping.add("Coconut Jelly");
                    }
                    int qty = Integer.parseInt(txtQty.getText().toString());
                    temp_subtotal *= qty;
                    orderList.add(new Order(temp_tipe,arrTopping,qty,temp_subtotal));
                    orderAdapter.notifyDataSetChanged();
                    total += temp_subtotal;

                    txtInfo.setText("Hi, " + edName.getText().toString() + " Total : Rp " + String.valueOf(total));

                    //CLEAR
                    rbTea.setChecked(true);
                    cbCoconut.setChecked(false);
                    cbRedBean.setChecked(false);
                    cbPudding.setChecked(false);
                    cbPearl.setChecked(false);
                    txtQty.setText("1");
                }
            }
        });

        //BUTTON DELETE
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(indexClick != -1){
                    total -= orderList.get(indexClick).getSubtotal();
                    txtInfo.setText("Hi, " + edName.getText().toString() + " Total : Rp " + String.valueOf(total));
                    orderList.remove(indexClick);
                    orderAdapter.notifyDataSetChanged();

                    //CLEAR
                    rbTea.setChecked(true);
                    cbCoconut.setChecked(false);
                    cbRedBean.setChecked(false);
                    cbPudding.setChecked(false);
                    cbPearl.setChecked(false);
                    txtQty.setText("1");
                }
            }
        });


        //BUTTON RESET
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edName.setText("");
                rbTea.setChecked(true);
                cbCoconut.setChecked(false);
                cbRedBean.setChecked(false);
                cbPudding.setChecked(false);
                cbPearl.setChecked(false);
                txtQty.setText("1");
                orderList.clear();
                orderAdapter.notifyDataSetChanged();
                txtInfo.setText("Hi, Cust Total : Rp 0");
                total = 0;
            }
        });

    }
}
