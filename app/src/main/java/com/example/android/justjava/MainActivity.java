/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, getName()));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        Log.i("submitOrder", createOrderSummary(price));
        CharSequence text = "Order sent!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Calculates the price of the order.
     * <p>
     * Makes use of whippedCreamIsChecked and chocolateSauceIsChecked in order to calculate topings variable
     *
     * @return total price, with a price per cup of £5, whippedCream £1, chocolateSauce £2
     */
    private int calculatePrice() {
        int basePrice = 5;
        if (chocolateSauceIsChecked() && whippedCreamIsChecked()) {
            basePrice += 3;
        } else if (whippedCreamIsChecked()) {
            basePrice += 1;
        } else if (chocolateSauceIsChecked()) {
            basePrice += 2;
        }
        return basePrice * quantity;
    }

    /**
     * Create an order summary
     *
     * @param price string of currency + price
     * @return an order summary String
     */
    private String createOrderSummary(int price) {
        return getString(R.string.order_summary_name, getName())
                + "\n" + getString(R.string.order_summary_whipped_cream, whippedCreamIsChecked())
                + "\n" + getString(R.string.order_summary_chocolate, chocolateSauceIsChecked())
                + "\n" + getString(R.string.order_summary_quantity, quantity)
                + "\n" + getString(R.string.order_summary_total, "£", price)
                + "\n" + getString(R.string.thank_you);
    }

    /**
     * Checks the state of the Whipped Cream CheckBox and returns a boolean
     *
     * @return a checked state boolean
     */
    private Boolean whippedCreamIsChecked() {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        return checkBox.isChecked();
    }

    /**
     * Checks the state of the Chocolate Sauce CheckBox and returns a boolean
     *
     * @return a checked state boolean
     */
    private Boolean chocolateSauceIsChecked() {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_sauce_checkbox);
        return checkBox.isChecked();
    }

    /**
     * Gets the text from the Name field
     *
     * @return a string with the name
     */
    private String getName() {
        final EditText name = (EditText) findViewById(R.id.name_field);
        return name.getText().toString();
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            CharSequence text = "You've reached our maximum limit for coffee orders!";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            CharSequence text = "You've reached our lower limit for coffee orders!";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }
}