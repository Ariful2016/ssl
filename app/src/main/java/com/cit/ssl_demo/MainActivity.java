package com.cit.ssl_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class MainActivity extends AppCompatActivity implements SSLCTransactionResponseListener {

    double price = 1000;
    TextView textView;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_layout);
        textView = findViewById(R.id.amount_txt);

        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization("creat613377970f2ed", "creat613377970f2ed@ssl", price, SSLCCurrencyType.BDT, "123456789098765", "yourProductType", SSLCSdkType.TESTBOX);


        IntegrateSSLCommerz
                .getInstance(MainActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .buildApiCall(this);
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {

        textView.setText(sslcTransactionInfoModel.getAmount()+" BDT");
        layout.setVisibility(View.VISIBLE);

    }

    @Override
    public void transactionFail(String s) {

    }

    @Override
    public void merchantValidationError(String s) {

    }
}