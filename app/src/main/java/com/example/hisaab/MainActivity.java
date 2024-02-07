package com.example.hisaab;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edtTitle,edtAmount;
   Button btnAdd,btnHistory,btnReset;

  ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAmount=findViewById(R.id.edtAmount);
        edtTitle=findViewById(R.id.edtTitle);
        btnAdd=findViewById(R.id.btnAdd);
        btnHistory=findViewById(R.id.btnHistory);
        listView=findViewById(R.id.listView);


        DatabaseHelper databaseHelper=DatabaseHelper.getDB(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=edtTitle.getText().toString();
                String amount=edtAmount.getText().toString();
                databaseHelper.expenseDao().addTx(new Expense(title,amount));

               edtTitle.setText("");
               edtAmount.setText("");
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ArrayList<Expense> arrExpense=(ArrayList<Expense>) databaseHelper.expenseDao().getAllExpense();
                ArrayList<String> arrexpense1=new ArrayList<>();

                for(int i=arrExpense.size()-1;i>=0;i--)
                {
                    arrexpense1.add(arrExpense.get(i).getTitle()+": "+arrExpense.get(i).getAmount());
                    Log.d("Data","Title :"+arrExpense.get(i).getTitle()+"Amount :"+arrExpense.get(i).getAmount());
                }


                ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,arrexpense1);
                listView.setAdapter(adapter);




            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper.expenseDao().updateTx(new Expense());

            }
        });
    }
}

