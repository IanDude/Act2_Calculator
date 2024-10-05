package com.example.act2_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        solution = findViewById(R.id.solution);

        assignId(R.id.btnclear);
        assignId(R.id.btnbckspc);
        assignId(R.id.btndivide);
        assignId(R.id.btnmulti);
        assignId(R.id.btnminus);
        assignId(R.id.btnplus);
        assignId(R.id.btneqls);
        assignId(R.id.btndot);
        assignId(R.id.btnmod);
        assignId(R.id.btn0);
        assignId(R.id.btn1);
        assignId(R.id.btn2);
        assignId(R.id.btn3);
        assignId(R.id.btn4);
        assignId(R.id.btn5);
        assignId(R.id.btn6);
        assignId(R.id.btn7);
        assignId(R.id.btn8);
        assignId(R.id.btn9);
    }
    public void assignId(int id){
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        String buttontext = ((MaterialButton)view).getText().toString();
        String dataToCalculate = solution.getText().toString();
        //resets everything back
        if (buttontext.equals("C")){
            solution.setText("");
            return;
        }
        //solves the input
        if(buttontext.equals("=")){
                if (dataToCalculate.isEmpty()){
                    Toast.makeText(this,"Please input number.",Toast.LENGTH_SHORT).show();
                    return;
                }
                String finalResult = getResult(dataToCalculate);
                if(!finalResult.equals("Error")){
                    solution.setText(finalResult);
                }
            return;
        }
        //checks if button clicked is % and calculates it already to the decimal point
        if (buttontext.equals("%")) {
            if (!dataToCalculate.isEmpty() && isNumeric(lastOperand(dataToCalculate))) {
                // Get the last operand and convert it to percentage
                String lastOperand = lastOperand(dataToCalculate);
                double percentValue = Double.parseDouble(lastOperand) / 100.0;
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - lastOperand.length()) + percentValue;
                solution.setText(dataToCalculate);
            }
            return;
        }
        //removes last added item
        if(buttontext.equals("⌫")){
            if(!dataToCalculate.isEmpty()){
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

            }
        }else if(isOperator(buttontext)) {
            //checks if isOperator function is true and if there is existing number in result, then appending it for the next solution
            if (!dataToCalculate.isEmpty() && isLastCharOpe(dataToCalculate)){
                return;
            }
            dataToCalculate += buttontext;

        }else{
            dataToCalculate += buttontext;
        }

        solution.setText(dataToCalculate);

    }
    //checks if an operator button is clicked
    private boolean isOperator(String buttonope){
        return buttonope.equals("+") || buttonope.equals("-") ||
                buttonope.equals("x") || buttonope.equals("÷");
    }
    //calculate the result for all the inputs
    public String getResult(String data){
        data = convertDoubleOperators(data);
        data = data.replace("x","*");
        data = data.replace("÷","/");
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }finally {
            Context.exit();
        }
    }

    //double checks for sign integer computations
    private String convertDoubleOperators(String data){
        data = data.replaceAll("--","+");
        data = data.replaceAll("\\+-","-");
        data = data.replaceAll("-\\+","-");
        data = data.replaceAll("\\+\\+","+");
        if (data.startsWith("+")){
            data = data.substring(1);
        }
        return data;
    }
    //checks if the last input is operator then prevents inputting another operator
    private boolean isLastCharOpe(String data){
        if (data.isEmpty()){
            return false;
        }
        char lastChar = data.charAt(data.length()-1);
        return isOperator(String.valueOf(lastChar));
    }
    //checks if current solution is numeric only
    private boolean isNumeric(String data){
        try{
            Double.parseDouble(data);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private String lastOperand(String data) {
        String[] tokens = data.split("[-+x/]");
        return tokens[tokens.length - 1];
    }

    }
