package com.example.lab_3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements dialog.dialogListener{
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Кнопка номер 1 нажата", Toast.LENGTH_SHORT);
                toast.show();
            };
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Кнопка номер 2 нажата", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        final String[] listItems = new String[]{"D-Link DGS-1100-06", "D-Link DES-1005C/B1A", "D-Link DGS-1024D/I1A", "D-Link DXS-3610-54S/A1ASI", "D-Link DGS-1210-10/ME"};
        final String[] correctItems = new String[]{"D-Link DGS-1100-06", "D-Link DXS-3610-54S/A1ASI"};
        final boolean[] checkedItems = new boolean[listItems.length];
        final List<String> selectedItems = Arrays.asList(listItems);
        List<String> correctItemsList = Arrays.asList(correctItems);
        List<String> selected = new ArrayList<String>();

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected.clear();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Выберите коммутаторы 3-ео уровня")
                        .setMultiChoiceItems(listItems, checkedItems, (dialog, which, isChecked) -> {
                            checkedItems[which] = isChecked;
                            String currentItem = selectedItems.get(which);
                        })
                        .setCancelable(false)
                        .setNegativeButton("Отмена", (dialog, which) -> {})
                        .setPositiveButton("Принять",  (dialog, which) -> {
                            for (int i = 0; i < checkedItems.length; i++) {
                                if (checkedItems[i]) {
                                    selected.add(selectedItems.get(i));
                                }
                            }
                            Log.d("DEBUG", String.valueOf(selected));
                            Log.d("DEBUG", String.valueOf(correctItemsList));
                            if (correctItemsList.equals(selected)) {
                                Toast.makeText(MainActivity.this, "Правильный ответ!", Toast.LENGTH_LONG).show();
                            } else {
                                btn_1.setVisibility(View.INVISIBLE);
                                btn_2.setVisibility(View.INVISIBLE);
                                btn_3.setVisibility(View.INVISIBLE);
                                btn_4.setVisibility(View.INVISIBLE);
                            }
                        })
                        .setNeutralButton("Сброс", (dialog, which) -> {
                            Arrays.fill(checkedItems, false);
                        });

                builder.create();
                AlertDialog alertdialog = builder.create();
                alertdialog.show();
            }
        });
    };
    public void openDialog() {
        dialog dialog = new dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }
    @Override
    public void apply(Boolean apply) {
        if (apply) {
            btn_1.setTextColor(Color.RED);
            btn_2.setTextColor(Color.RED);
            btn_3.setTextColor(Color.RED);
            btn_4.setTextColor(Color.RED);
        }
        else {
            Toast.makeText(MainActivity.this, "Диалоговое окно закрыто", Toast.LENGTH_LONG).show();
        }
    }
}