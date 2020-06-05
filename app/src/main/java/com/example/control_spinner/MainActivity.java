package com.example.control_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_spinner.exceptions.*;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private Spinner spinner1;
    private TextView textViewRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.et_num1);
        editText2 = findViewById(R.id.et_num2);
        spinner1 = findViewById(R.id.spinner);
        textViewRes = findViewById(R.id.tv_result);

        String[] options = {"Add", "Subtract", "Multiply", "Divide"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_eduardosoto, options);
        spinner1.setAdapter(adapter);

        calculate(new View(this));
    }

    public void calculate(View view){
        try {
            String value1_String = editText1.getText().toString();
            String value2_String = editText2.getText().toString();

            if(value1_String.equals("") || value2_String.equals(""))
                throw new NonNumberTypedException();

            int value1 = Integer.parseInt(value1_String);
            int value2 = Integer.parseInt(value2_String);
            int result;

            String selection = spinner1.getSelectedItem().toString();

            switch (selection) {
                case "Add":
                    result = value1 + value2;
                    break;
                case "Subtract":
                    result = value1 - value2;
                    break;
                case "Multiply":
                    result = value1 * value2;
                    break;
                case "Divide":
                    if (value2 != 0) {
                        result = value1 / value2;
                    } else {
                        throw new DividedByZeroException();
                    }
                    break;
                default:
                    throw new NonOperationSelectedException();
            }

            textViewRes.setText(String.valueOf(result));

        } catch (NonOperationSelectedException e) {
            Toast.makeText(this, "Select an operation", Toast.LENGTH_SHORT).show();
        } catch (NonNumberTypedException e) {
            Toast.makeText(this, "Type both numbers", Toast.LENGTH_SHORT).show();
        } catch (DividedByZeroException e) {
            Toast.makeText(this, "Can't divide by zero", Toast.LENGTH_SHORT).show();
        }
    }
}