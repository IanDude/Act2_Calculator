package com.example.act2_calculator;

import android.annotation.SuppressLint;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import java.text.DecimalFormat;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {

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

        Button rotation = findViewById(R.id.btnrot);
        rotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentOrientation = getResources().getConfiguration().orientation;
                if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                }
                Log.d("MainActivity", "Current Orientation: " + currentOrientation);

            }
        });

        Button sevenButton = findViewById(R.id.btn7);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel7 = getString(R.string.n7); // Fetch the string resource for "7"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "7"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel7;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "7"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel7);
                }
                // If it ends with "%", append "×" plus the string resource for "7"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel7);
                }
                // Otherwise, just append the string resource for "7"
                else {
                    updateDisplayText(buttonLabel7);
                }
            }
        });




        Button eightButton = findViewById(R.id.btn8);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel8 = getString(R.string.n8); // Fetch the string resource for "8"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "8"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel8;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "8"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel8);
                }
                // If it ends with "%", append "×" plus the string resource for "8"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel8);
                }
                // Otherwise, just append the string resource for "8"
                else {
                    updateDisplayText(buttonLabel8);
                }
            }
        });


        Button nineButton = findViewById(R.id.btn9);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel9 = getString(R.string.n9); // Fetch the string resource for "9"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "9"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel9;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "9"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel9);
                }
                // If it ends with "%", append "×" plus the string resource for "9"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel9);
                }
                // Otherwise, just append the string resource for "9"
                else {
                    updateDisplayText(buttonLabel9);
                }
            }
        });


        Button fourButton = findViewById(R.id.btn4);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel4 = getString(R.string.n4); // Fetch the string resource for "4"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "4"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel4;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "4"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel4);
                }
                // If it ends with "%", append "×" plus the string resource for "4"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel4);
                }
                // Otherwise, just append the string resource for "4"
                else {
                    updateDisplayText(buttonLabel4);
                }
            }
        });

        Button fiveButton = findViewById(R.id.btn5);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel5 = getString(R.string.n5); // Fetch the string resource for "5"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "5"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel5;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "5"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel5);
                }
                // If it ends with "%", append "×" plus the string resource for "5"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel5);
                }
                // Otherwise, just append the string resource for "5"
                else {
                    updateDisplayText(buttonLabel5);
                }
            }
        });


        Button sixButton = findViewById(R.id.btn6);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel6 = getString(R.string.n6); // Fetch the string resource for "6"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "6"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel6;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "6"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel6);
                }
                // If it ends with "%", append "×" plus the string resource for "6"
                else if (currentText.endsWith(getString(R.string.n6))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel6);
                }
                // Otherwise, just append the string resource for "6"
                else {
                    updateDisplayText(buttonLabel6);
                }
            }
        });


        Button oneButton = findViewById(R.id.btn6);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel1 = getString(R.string.n1); // Fetch the string resource for "1"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "1"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel1;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "1"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel1);
                }
                // If it ends with "%", append "×" plus the string resource for "1"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel1);
                }
                // Otherwise, just append the string resource for "1"
                else {
                    updateDisplayText(buttonLabel1);
                }
            }
        });


        Button twoButton = findViewById(R.id.btn2);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel2 = getString(R.string.n2); // Fetch the string resource for "2"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "2"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel2;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "2"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel2);
                }
                // If it ends with "%", append "×" plus the string resource for "2"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel2);
                }
                // Otherwise, just append the string resource for "2"
                else {
                    updateDisplayText(buttonLabel2);
                }
            }
        });


        Button threeButton = findViewById(R.id.btn3);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel3 = getString(R.string.n3); // Fetch the string resource for "3"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "3"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel3;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "3"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel3);
                }
                // If it ends with "%", append "×" plus the string resource for "3"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel3);
                }
                // Otherwise, just append the string resource for "3"
                else {
                    updateDisplayText(buttonLabel3);
                }
            }
        });


        Button zeroButton = findViewById(R.id.btn0);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabel0 = getString(R.string.n0); // Fetch the string resource for "0"

                // Check if the current text is not empty and the last character is "0"
                if (!currentText.isEmpty() && currentText.length() > 1) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    char secondLastChar = currentText.charAt(currentText.length() - 2);

                    // Check if the last character is "0" and the character before is an operator
                    if (lastChar == '0' && isOperator(secondLastChar)) {
                        // Replace the last character "0" with the string resource for "0"
                        currentText = currentText.substring(0, currentText.length() - 1) + buttonLabel0;
                        displayText.setText(currentText);
                        return; // Stop here since we already replaced the "0"
                    }
                }

                // If the current text equals the previous result, set it to the string resource for "0"
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabel0);
                }
                // If it ends with "%", append "×" plus the string resource for "0"
                else if (currentText.endsWith(getString(R.string.Percent))) {
                    updateDisplayText(getString(R.string.Mult) + buttonLabel0);
                }
                // Otherwise, just append the string resource for "0"
                else {
                    updateDisplayText(buttonLabel0);
                }
            }
        });


        Button dotButton = findViewById(R.id.btndot);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();
                String buttonLabelDot = getString(R.string.Dot); // Fetch the string resource for "."

                // Extract the last number from the expression
                String lastNumber = "";
                for (int i = currentText.length() - 1; i >= 0; i--) {
                    char c = currentText.charAt(i);
                    if (isOperator(c) || c == ' ') {
                        break;
                    }
                    lastNumber = String.format("%s%s", c, lastNumber);
                }

                // Check if the last number contains a dot
                if (currentText.equals(previousResult)) {
                    displayText.setText(buttonLabelDot.equals(".") ? "0." : "0" + buttonLabelDot);
                }
                else if (!lastNumber.contains(".")) {
                    // If there is no number before the dot or there is an operator, add a "0" before the dot
                    if (lastNumber.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1)) || lastNumber.equals("−")) {
                        displayText.setText(String.format("%s0%s", currentText, buttonLabelDot));
                    } else {
                        displayText.setText(currentText + buttonLabelDot);
                    }
                    adjustTextSize(displayText); // Adjust text size
                }
            }
        });




        Button cButton = findViewById(R.id.btnclear);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String buttonLabelClear = getString(R.string.Clear); // Fetch the string resource for "C"

                // Clear the display text
                displayText.setText(buttonLabelClear.equals("C") ? "" : buttonLabelClear);
                adjustTextSize(displayText); // Adjust text size
            }
        });


        Button delButton = findViewById(R.id.btnbckspc);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                // If the current text is empty, do nothing
                if (currentText.isEmpty()) {
                    displayText.setText("");
                } else {
                    // Remove the last character
                    currentText = currentText.substring(0, currentText.length() - 1);
                    displayText.setText(currentText.isEmpty() ? "" : currentText);
                }

                adjustTextSize(displayText); // Adjust text size
            }
        });



        Button percentButton = findViewById(R.id.btnmod);
        percentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                // Check if the current text is empty or ends with an operator
                if (currentText.isEmpty()) {
                    displayText.setText(currentText); // Do nothing if empty
                } else if (currentText.endsWith(getString(R.string.Percent)) ||
                        currentText.endsWith(getString(R.string.Plus)) ||
                        currentText.endsWith(getString(R.string.Div)) ||
                        currentText.endsWith(getString(R.string.Mult)) ||
                        currentText.endsWith(getString(R.string.Percent))) {
                    displayText.setText(currentText);
                } else {
                    updateDisplayText("%"); // Update display with percent sign
                }
            }
        });


        Button divideButton = findViewById(R.id.btndivide);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                if (currentText.isEmpty()) {
                    displayText.setText(""); // Do nothing if empty
                } else {
                    // Check if the current text ends with an operator
                    if (currentText.endsWith(getString(R.string.Minus)) ||
                            currentText.endsWith(getString(R.string.Div)) ||
                            currentText.endsWith(getString(R.string.Mult))) {

                        // Replace the last operator with the divide operator
                        currentText = currentText.substring(0, currentText.length() - 1) + getString(R.string.Div);
                        displayText.setText(currentText);

                    } else if (currentText.endsWith(getString(R.string.Minus))) {
                        updateDisplayText(""); // Handle specific case for minus operator
                    } else {
                        updateDisplayText(getString(R.string.Div)); // Append divide operator
                    }
                }
            }
        });


        Button multiplyButton = findViewById(R.id.btnmulti);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                if (currentText.isEmpty()) {
                    displayText.setText(""); // Do nothing if empty
                } else {
                    // Check if the current text ends with an operator
                    if (currentText.endsWith(getString(R.string.Plus)) ||
                            currentText.endsWith(getString(R.string.Div)) ||
                            currentText.endsWith(getString(R.string.Mult))) {

                        // Replace the last operator with the multiply operator
                        currentText = currentText.substring(0, currentText.length() - 1) + getString(R.string.Mult);
                        displayText.setText(currentText);

                    } else if (currentText.endsWith(getString(R.string.Minus))) {
                        updateDisplayText(""); // Handle specific case for minus operator
                    } else {
                        updateDisplayText(getString(R.string.Mult)); // Append multiply operator
                    }
                }
            }
        });


        Button minusButton = findViewById(R.id.btnminus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current text from the display
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                // Check if the displayed text is empty
                if (currentText.isEmpty()) {
                    displayText.setText(getString(R.string.Minus)); // Set to minus operator
                    return;
                }

                // Count consecutive minus signs at the end
                int minusCount = 0;
                for (int i = currentText.length() - 1; i >= 0 && currentText.charAt(i) == getString(R.string.Minus).charAt(0); i--) {
                    minusCount++;
                }

                // Only add minus if there are fewer than 2 consecutive minus signs
                if (minusCount < 1) {
                    displayText.setText(String.format("%s%s", currentText, getString(R.string.Minus))); // Append minus sign
                }
            }
        });







        Button addButton = findViewById(R.id.btnplus);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                if (currentText.isEmpty()) {
                    displayText.setText("");
                } else {
                    if (currentText.endsWith(getString(R.string.Plus))
                            || currentText.endsWith(getString(R.string.Div))
                            || currentText.endsWith(getString(R.string.Mult))) {

                        // Replace the last operator with addition operator
                        currentText = currentText.substring(0, currentText.length() - 1) + getString(R.string.Plus);
                        displayText.setText(currentText);

                    } else if (currentText.endsWith(getString(R.string.Minus))) {
                        updateDisplayText(""); // Handle if the last operator is minus
                    } else {
                        updateDisplayText(getString(R.string.Plus)); // Append addition operator
                    }
                }
            }
        });

        // Declare a variable to store the computed value, initialized with "/"


        Button equalButton = findViewById(R.id.btneqls);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                TextView displayText = findViewById(R.id.solution);
                String currentText = displayText.getText().toString();

                // Replace symbols for division and multiplication with JavaScript-friendly operators
                currentText = currentText.replaceAll(getString(R.string.Div), "/");
                currentText = currentText.replaceAll(getString(R.string.Mult), "*");
                currentText = currentText.replaceAll(getString(R.string.Minus), "-");

                // Remove all spaces
                currentText = currentText.replaceAll("\\s+", "");

                // Handle percentage
                currentText = currentText.replaceAll("%", "/100");

                // Check if the last character is an operator
                if (!currentText.isEmpty() && (
                        currentText.endsWith("+") || currentText.endsWith("-") ||
                                currentText.endsWith("*") || currentText.endsWith("/"))) {
                    Toast.makeText(getApplicationContext(), getString(R.string.invalid_expression_ends_with_operator), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check for division by zero
                if (currentText.matches(".*/0(\\D|$)") || currentText.matches(".*/0\\.$")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.you_cannot_divide_by_zero), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Log the expression being evaluated
                Log.d("Calculator", "Evaluating: " + currentText);

                // Evaluate the expression
                try {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                    Object result = engine.eval(currentText);

                    // Check for Infinity or -Infinity results
                    if (result instanceof Double && (Double.isInfinite((Double) result) || Double.isNaN((Double) result))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.division_leads_to_infinity), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Format the result
                    DecimalFormat df = new DecimalFormat("0.######");
                    previousResult = df.format(result); // Store result

                    // Update the display
                    displayText.setText(previousResult);
                } catch (ScriptException e) {
                    // Send a toast message with the error information
                    Toast.makeText(getApplicationContext(), getString(R.string.error_evaluating_expression), Toast.LENGTH_SHORT).show();
                    // Display the current expression instead of the error message
                    displayText.setText(currentText);
                    Log.e("Calculator", "Error evaluating expression", e);
                }
            }
        });






    }
    @SuppressLint("SetTextI18n")
    private void updateDisplayText(String newText) {
        TextView displayText = findViewById(R.id.solution);
        String currentText = displayText.getText().toString();

        // If the current text is empty, replace it with the new text
        if (currentText.isEmpty()) {
            displayText.setText(newText);
        } else {
            displayText.setText(currentText + newText);
        }

        // Adjust the text size based on the current length of the string
        adjustTextSize(displayText);
    }


    // Function to check if a character is an operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '%';
    }

    // Function to adjust text size based on character hello test
    private void adjustTextSize(TextView displayText) {
        String text = displayText.getText().toString();
        if (text.length() > 18 && text.length() <= 25) {
            displayText.setTextSize(56); // Adjust this value as necessary
        }else if(text.length() > 25 && text.length() <= 38){
            displayText.setTextSize(50);
        }else if(text.length() > 38 && text.length() <= 42){
            displayText.setTextSize(40);
        }else if(text.length() > 46){
            displayText.setTextSize(34);
        }else {
            displayText.setTextSize(60); // Reset to default size
        }
    }
    private String previousResult = "/";

}
