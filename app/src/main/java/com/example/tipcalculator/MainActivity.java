/**
 * This is the main activity that implements a calculate that is used to calculate Tips.
 *
 * @author Arturo Andazola Trevizo
 * @version 9/8/2024
 */
package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tipcalculator.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        int defaultValue = 0;

        binding.zeroPercentTip.setChecked(true);

        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        binding.tipAmount.setText(cf.format(defaultValue));
        binding.grandTotal.setText(cf.format(defaultValue));

    }

    // Clear function used to clear inputs and outputs
    // Clear function is used by the clear button
    public void clear(View view){
        int defaultValue = 0;
        binding.totalAmount.setText("");
        binding.taxAmount.setText("");
        binding.zeroPercentTip.setChecked(true);
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        binding.tipAmount.setText(cf.format(defaultValue));
        binding.grandTotal.setText(cf.format(defaultValue));

    }

    // Calculate function is used to calculate the tip and grand total
    // by using the inputs total, tax amount, and tip percentage
    // Calculate function is used by the calculate button
    public void calculate(View view){
        double total, tax, tip, tipPercentage,  grandTotal;

        try{
            total = Double.parseDouble(binding.totalAmount.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(this, "Total Amount can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            tax = Double.parseDouble(binding.taxAmount.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(this, "Tax Amount can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if(binding.zeroPercentTip.isChecked()){
            tipPercentage = 0;
        }else if(binding.fivePercentTip.isChecked()){
            tipPercentage = 0.05;
        }else if(binding.tenPercentTip.isChecked()){
            tipPercentage = 0.10;
        }else{
            tipPercentage = 0.20;
        }

        tip = total * tipPercentage;
        grandTotal = tip + total + tax;

        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        binding.tipAmount.setText(cf.format(tip));
        binding.grandTotal.setText(cf.format(grandTotal));

    }
}