/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price, with a price per cup of $5
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Create an order summary
     *
     * @param price
     * @return an order summary String
     */
    private String createOrderSummary (int price){
        return "Name:Alan"
                + "\nAdd Whipped Cream? " + whippedCreamIsChecked()
                + "\nAdd Chocolate Sauce? " + chocolateSauceIsChecked()
                + "\nQuantity: " + quantity
                + "\nTotal: £" + price
                + "\nThank you!";
    };

    /**
     * Checks the state of the Whipped Cream CheckBox and returns a boolean
     *
     * @return a checked state boolean
     */
    private Boolean whippedCreamIsChecked () {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        return checkBox.isChecked();
    };

    /**
     * Checks the state of the Chocolate Sauce CheckBox and returns a boolean
     *
     * @return a checked state boolean
     */
    private Boolean chocolateSauceIsChecked () {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_sauce_checkbox);
        return checkBox.isChecked();
    };


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
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

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}