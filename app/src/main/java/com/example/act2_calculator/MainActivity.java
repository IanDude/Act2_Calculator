package com.example.act2_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
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

    TextView solution, result;
    MaterialButton btnclear,btnbckspc,btndivide,btnmulti,btnminus,btnplus,btnequals,btndot,btnmod,
            btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, switchtheme;


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
        result = findViewById(R.id.result);

        assignId(btnclear,R.id.btnclear);
        assignId(btnbckspc,R.id.btnbckspc);
        assignId(btndivide,R.id.btndivide);
        assignId(btnmulti,R.id.btnmulti);
        assignId(btnminus,R.id.btnminus);
        assignId(btnplus,R.id.btnplus);
        assignId(btnequals,R.id.btneqls);
        assignId(btndot,R.id.btndot);
        assignId(btnmod,R.id.btnmod);
        assignId(btn0,R.id.btn0);
        assignId(btn1,R.id.btn1);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn7,R.id.btn7);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);
    }
    public void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        String buttontext = ((MaterialButton)view).getText().toString();
        String dataToCalculate = solution.getText().toString();
        String currentResult = result.getText().toString();
        //resets everything back
        if (buttontext.equals("C")){
            solution.setText("");
            result.setText("0");
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
                    result.setText(finalResult);
                    solution.setText("");
                }
            return;
        }
        //checks if button clicked is % and calculates it already to the decimal point
        if (buttontext.equals("%")){
            if (!dataToCalculate.isEmpty() && isNumeric(dataToCalculate)){
                Double value = Double.parseDouble(dataToCalculate)/100.0;
                result.setText(String.valueOf(value));
                solution.setText("");
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
            if (!currentResult.equals("0")) {
                dataToCalculate = currentResult;
            }
            dataToCalculate += buttontext;
            result.setText("0");

        }else{
            dataToCalculate += buttontext;
        }
        solution.setText(dataToCalculate);

    }
    //checks if an operator button is clicked
    private boolean isOperator(String buttonope){
        return buttonope.equals("+") || buttonope.equals("-") ||
                buttonope.equals("x") || buttonope.equals("/");
    }
    //calculate the result for all the inputs
    public String getResult(String data){
        data = convertDoubleOperators(data);
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
            e.printStackTrace();
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

    }
