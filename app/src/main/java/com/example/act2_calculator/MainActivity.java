package com.example.act2_calculator;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution, result;

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

        // Restore the state if available
        if (savedInstanceState != null) {
            String savedSolution = savedInstanceState.getString("solutionText");
            solution.setText(savedSolution);
        }

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
        assignId(R.id.btnrot);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current solution text
        outState.putString("solutionText", solution.getText().toString());
    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the solution text
        String savedSolution = savedInstanceState.getString("solutionText");
        solution.setText(savedSolution);
    }

    public void assignId(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        String buttontext = ((MaterialButton) view).getText().toString();
        String dataToCalculate = solution.getText().toString();
        String currentResult = result.getText().toString();

        // Handle backspace button ("⌫")
        if (buttontext.equals("⌫")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
            solution.setText(dataToCalculate);
            adjustSolutionTextSize(); // Check text size after deleting
            return;
        }

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
        // Resets everything back with clear button ("C")
        if (buttontext.equals("C")) {
            solution.setText("");
            result.setText("");
            return;
        }

        // Allow equal ("=") button to function regardless of the length
        if (buttontext.equals("=")) {
            if (dataToCalculate.isEmpty()) {
                return;
            }
            String finalResult = getResult(dataToCalculate);
            if (!finalResult.equals("Error")) {
                result.setText(finalResult);
                adjustSolutionTextSizeRes();
            }
            return;
        }

        // Check if solution text length is already 80, block further input (except for backspace, clear, and equals)
        if (dataToCalculate.length() >= 50) {
            Toast.makeText(this, "Maximum input length reached", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle case when a number is clicked right after a result is displayed
        if (!currentResult.isEmpty()) {
            // If a number is pressed, reset the solution text
            if (isNumeric(buttontext)) {
                dataToCalculate = buttontext; // Clear and set number
                result.setText(""); // Clear the result
            } else if (isOperator(buttontext)) {
                // If an operator is pressed, append to the current result
                dataToCalculate = currentResult + buttontext;
                result.setText(""); // Clear the result for next calculation
            }
            solution.setText(dataToCalculate);
            adjustSolutionTextSize(); // Check text size after setting the solution
            return;
        }
        // Rotation button logic
        if (buttontext.equals("↺")) {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            Log.d("MainActivity", "Current Orientation: " + currentOrientation);
            return;
        }

        // Handle dot input (".")
        if (buttontext.equals(".")) {
            if (dataToCalculate.isEmpty() || isLastCharOpe(dataToCalculate)) {
                // If there's no number or an operator, append "0."
                dataToCalculate += "0.";
            } else {
                // Get the last operand (number) to check for an existing dot
                String lastOperand = lastOperand(dataToCalculate);
                if (!lastOperand.contains(".")) {
                    dataToCalculate += ".";
                }
            }
            solution.setText(dataToCalculate);
            adjustSolutionTextSize(); // Check text size after setting the solution
            return;
        }

        // "%" button logic
        if (buttontext.equals("%")) {
            if (dataToCalculate.isEmpty()) {
                return;
            }
            if (isLastCharOpe(dataToCalculate)) {
                solution.setText(dataToCalculate);
            }else if(dataToCalculate.endsWith(".")){
                return;
            }else {
                dataToCalculate += "%";
                solution.setText(dataToCalculate);
            }
            adjustSolutionTextSize(); // Check text size after setting the solution
            return;
        }

        // Add input (non-backspace, non-clear, and non-equals buttons)
        if (isOperator(buttontext)) {
            if(dataToCalculate.isEmpty() && !buttontext.equals("-")){

                return;
            }
            if(dataToCalculate.length() == 1 && dataToCalculate.equals("-")) {
                return;
            }
            if (!dataToCalculate.isEmpty() && isLastCharOpe(dataToCalculate) && !dataToCalculate.endsWith("%")) {

                if (dataToCalculate.endsWith("-") && buttontext.equals("-")) {
                    char charBeforeMinus = dataToCalculate.length() > 1 ? dataToCalculate.charAt(dataToCalculate.length() - 2) : '\0';
                    if (Character.isDigit(charBeforeMinus)) {
                        dataToCalculate += "-";
                    } else {
                        dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1) + buttontext;
                    }
                }else if (dataToCalculate.endsWith("+") && buttontext.equals("-")) {
                    char charBeforeMinus = dataToCalculate.length() > 1 ? dataToCalculate.charAt(dataToCalculate.length() - 2) : '\0';
                    if (Character.isDigit(charBeforeMinus)) {
                        dataToCalculate += "-";
                    } else {
                        dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1) + buttontext;
                    }
                }else if (dataToCalculate.endsWith("×") && buttontext.equals("-")) {
                    char charBeforeMinus = dataToCalculate.length() > 1 ? dataToCalculate.charAt(dataToCalculate.length() - 2) : '\0';
                    if (Character.isDigit(charBeforeMinus)) {
                        dataToCalculate += "-";
                    } else {
                        dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1) + buttontext;
                    }
                }else if (dataToCalculate.endsWith("-") && !buttontext.equals("-")) {
                    return;
                }  else {
                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1) + buttontext;
                }
            }
            else {
                dataToCalculate += buttontext;
            }

        } else {
            dataToCalculate += buttontext;
        }
        solution.setText(dataToCalculate);
        adjustSolutionTextSize(); // Check text size after setting the solution
    }
    // Method to check text length and adjust text size
    private void adjustSolutionTextSize() {
        if (solution.getText().length() > 6 && (solution.getText().length() < 11)) {
            solution.setTextSize(50); // Set to a smaller text size as needed
        }else if (solution.getText().length() >= 11 && (solution.getText().length() < 38)){
            solution.setTextSize(30);
        }else if (solution.getText().length() >= 38){
            solution.setTextSize(20);
        }else {
            solution.setTextSize(50); // Set to default text size if below threshold
        }
    }
    private void adjustSolutionTextSizeRes() {
        if (result.getText().length() > 6 && (result.getText().length() < 11)) {
            result.setTextSize(26); // Set to a smaller text size as needed

        }else {
            result.setTextSize(30); // Set to default text size if below threshold
        }
    }

    // Checks if an operator button is clicked
    private boolean isOperator(String buttonope) {
        return buttonope.equals("+") || buttonope.equals("-") ||
                buttonope.equals("×") || buttonope.equals("÷") ||
                buttonope.equals("%");
    }

    // Calculate the result for all the inputs
    public String getResult(String data) {
        // Remove all spaces
        data = data.replaceAll("\\s+", "");
        // Remove the last character if it's an operator
        if (!data.isEmpty() && isOperator(String.valueOf(data.charAt(data.length() - 1)))) {
            data = data.substring(0, data.length() - 1); // Remove the last character
        }
        // Check if the last character is an operator after potential removal
        if (!data.isEmpty() && (data.endsWith("+") || data.endsWith("-") ||
                data.endsWith("*") || data.endsWith("/") || data.endsWith("%"))) {
            Toast.makeText(this, getString(R.string.invalidExpression), Toast.LENGTH_SHORT).show();
            return "Error";
        }

        // Check for division by zero
        if (data.matches(".*/0(\\D|$)") || data.matches(".*/0\\.$") ||
                data.matches(".*/0\\s*") || data.matches("\\D*0\\s*\\D*0\\s*$")) {
            Toast.makeText(this, getString(R.string.zeroDivision), Toast.LENGTH_SHORT).show();
            return "Error";
        }

        data = convertDoubleOperators(data);
        data = data.replace("×", "*");
        data = data.replace("÷", "/");

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            // Check for Infinity or NaN results
            if (finalResult.equals("Infinity") || finalResult.equals("NaN")) {
                Toast.makeText(this, getString(R.string.zeroDivision), Toast.LENGTH_SHORT).show();
                return "Error";
            }

            // Format the result to remove trailing .0
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.substring(0, finalResult.length() - 2);
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }

    // Convert double operators into single operator
    private String convertDoubleOperators(String data) {
        return data.replaceAll("\\+{2,}", "+")
                .replaceAll("-{2,}", "-")
                .replaceAll("\\*{2,}", "*")
                .replaceAll("/{2,}", "/")
                .replaceAll("%{2,}", "%");
    }

    // Checks if the string is numeric
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if the last character is an operator
    public boolean isLastCharOpe(String data) {
        return !data.isEmpty() && isOperator(String.valueOf(data.charAt(data.length() - 1)));
    }

    // Get the last operand from the input string
    private String lastOperand(String data) {
        String[] operands = data.split("(?<=[-+×/])|(?=[-+×/])");
        return operands.length > 0 ? operands[operands.length - 1] : "";
    }


}
