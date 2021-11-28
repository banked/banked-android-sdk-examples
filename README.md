# Banked Android SDK Setup and Examples

## Further reading
You can find more information about Banked in our [Developer Docs](https://developer.banked.com/docs/introduction), including a guide on how to set up Payment Sessions.

## Setup
Banked Android SDK works on Android 5.0+ (API 21+) and Java 8+

```
implementation("com.banked:checkout:2.0.1-beta7")
```

## Quick start
1. Add the latest dependency to your app gradle
2. Set the API key and payment ID
```
Banked.apiKey = "Add your API key here"
```
3. Add the following to the parent ```onStart``` function
```
override fun onStart() {
    super.onStart()
    Banked.onStart(fragment = this)
} 
```
4. Add support for deep linking back into the SDK from the bank
    1. If you use a single activity and multiple fragments with the Jetpack navigation library, add the following:
        1. Add the navigation graph in your main activity in the application manifest
        ``` <nav-graph android:value="@navigation/your_nav_graph" />```

        2. Add the deep link format you will be using for your integration to the fragment you wish to get the callback into
        ```
         <fragment
            android:id="@+id/Fragment"
            android:name="<Add the fragment full qualified name which you want to open>">
            <deepLink app:uri="<Add the deep link format you will be using>" />
        </fragment>
        ```

    2. If you do not use the Jetpack navigation library, you need to add the deep link support into the app manaifest
    ```
    <activity android:name="<Your activity>">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data
                android:host="<deep link host>"
                android:scheme="<deep link scheme>" />
        </intent-filter>
    </activity>
    ```
5. Call the following to start a payment
    ```
    Banked.startPayment(
        fragment = this,
        paymentId = "Your payment ID",
        continueUrl = "Your continue URL"
    )
    ```

## Getting feedback to the application
In order to notify the calling application when the payment is a success/fail you can set a listener to listen for these events
```
Banked.onPaymentSessionListener = object:OnPaymentSessionListener {
    override fun onPaymentFailed(paymentResult: PaymentResult) {
        // Handle payment success
    }

    override fun onPaymentSuccess(paymentResult: PaymentResult) {
        // Handle payment success 
    }
}
```

## Example projects
There are examples of how to integrate the Banked SDK within this project. These are
- Kotlin integration via a fragment __(kotlin-fragment)__
- Kotlin integration via a fragment using the Jetpack navigation library __(navigation-components)__
- Java integration via an activity __(java-activity)__
