package com.banked.sdk.java;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.banked.checkout.Banked;
import com.banked.checkout.OnPaymentSessionListener;
import com.banked.checkout.feature.status.model.PaymentResult;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements OnPaymentSessionListener {

    private static final String API_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private static final String CONTINUE_URL = "XXXXXXXX";
    private static final String PAYMENT_ID = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button makePaymentButton = findViewById(R.id.make_payment);

        makePaymentButton.setOnClickListener(v -> Banked.startPayment(this, PAYMENT_ID, CONTINUE_URL));

        Banked.setOnPaymentSessionListener(this);
        Banked.setApiKey(API_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Banked.onStart(this);
    }

    @Override
    public void onPaymentFailed(@NotNull PaymentResult paymentResult) {
        Log.d("Banked", "Payment failed: " + paymentResult);
    }

    @Override
    public void onPaymentSuccess(@NotNull PaymentResult paymentResult) {
        Log.d("Banked", "Payment success: " + paymentResult);
    }

    @Override
    public void onPaymentAborted() {
        Log.d("Banked", "Payment aborted");
    }
}