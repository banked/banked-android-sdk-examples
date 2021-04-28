package com.banked.sdk.kotlin_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.banked.checkout.Banked
import com.banked.checkout.OnPaymentSessionListener

private const val API_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
private const val CONTINUE_URL = "XXXXXXXX"
private const val PAYMENT_ID = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

class PaymentFragment : Fragment(), OnPaymentSessionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Banked.onPaymentSessionListener = this
        Banked.apiKey = API_KEY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.make_payment).setOnClickListener { _ ->
            Banked.startPayment(
                fragment = this,
                paymentId = PAYMENT_ID,
                continueUrl = CONTINUE_URL
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Banked.onStart(fragment = this)
    }

    override fun onPaymentFailed() {
        Log.d("Banked", "Payment failed")
    }

    override fun onPaymentSuccess(
        paymentId: String,
        amountFormatted: String,
        providerName: String,
        payeeName: String
    ) {
        Log.d("Banked", "Payment success")
    }
}