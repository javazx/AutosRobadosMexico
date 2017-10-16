package auto.javax.valeriano.autosrobadosmexico;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AmisMainActivity extends AppCompatActivity {

    private WebView webViewAmis;
    Button button;
    Button limpiar;
    Button buttonPDF;
    EditText vin;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amis_main);

        mAdView = (AdView) findViewById(R.id.adViewAmis);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
                Log.i("Ads", String.valueOf(errorCode));
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed");
            }
        });

        webViewAmis = (WebView) findViewById(R.id.webView1Amis);
        webViewAmis.getSettings().setJavaScriptEnabled(true);
        webViewAmis.getSettings().setAllowContentAccess(false);
        webViewAmis.getSettings().setLoadWithOverviewMode(true);
        webViewAmis.getSettings().setUseWideViewPort(true);
        webViewAmis.getSettings().setSupportZoom(true);
        webViewAmis.getSettings().setAllowFileAccess(true);
        webViewAmis.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webViewAmis.getSettings().setBuiltInZoomControls(true);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.button2Amis);
        limpiar  = (Button) findViewById(R.id.button3Amis);
        buttonPDF = (Button) findViewById(R.id.button4Amis);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                vin = (EditText)findViewById(R.id.editTextAmis);
                if(vin.getText().toString().length() < 10){
                    Toast.makeText(AmisMainActivity.this, "NIV Invalido!", Toast.LENGTH_SHORT).show();
                }else{
                    webViewAmis.loadUrl("http://www.amis.com.mx/InformaWeb/ServletReporteRobo?vin="
                            + vin.getText().toString());
                }
            }

        });

        limpiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                vin = (EditText)findViewById(R.id.editTextAmis);
                vin.setText("");
            }

        });

        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWebPagePrint(webViewAmis);
            }
        });
    }

    public  void createWebPagePrint(WebView webView) {
		/*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;*/
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();
        String jobName = getString(R.string.repuve_doc) + "_PDF";
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setMediaSize(PrintAttributes.MediaSize.ISO_A5);
        PrintJob printJob = printManager.print(jobName, printAdapter, builder.build());

        if(printJob.isCompleted()){
            Toast.makeText(getApplicationContext(), "PDF Exitoso", Toast.LENGTH_LONG).show();
        }
        else if(printJob.isFailed()){
            Toast.makeText(getApplicationContext(), "PDF Erroneo", Toast.LENGTH_LONG).show();
        }
        // Save the job object for later status checking
    }
}
