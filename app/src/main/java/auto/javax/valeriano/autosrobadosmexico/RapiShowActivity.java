package auto.javax.valeriano.autosrobadosmexico;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class RapiShowActivity extends AppCompatActivity {

    private WebView webViewDataRapi;
    private Button buttonPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapi_show);

        String placa = getIntent().getStringExtra("placa");
        String captcha = getIntent().getStringExtra("captcha");

        webViewDataRapi = (WebView) findViewById(R.id.webView2RS);
        webViewDataRapi.getSettings().setJavaScriptEnabled(true);
        webViewDataRapi.getSettings().setAllowContentAccess(false);
        webViewDataRapi.getSettings().setLoadWithOverviewMode(true);
        webViewDataRapi.getSettings().setUseWideViewPort(true);
        webViewDataRapi.getSettings().setSupportZoom(true);
        webViewDataRapi.getSettings().setAllowFileAccess(true);
        webViewDataRapi.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewDataRapi.getSettings().setBuiltInZoomControls(true);
        webViewDataRapi.loadUrl("https://rapi.pgj.cdmx.gob.mx/RAPI/vehiculo?txtPlacas="
                + placa.toString() + "&" +"seccode=" + captcha.toString());

        addListenerOnButton();
    }

    public  void createWebPagePrint(WebView webView) {
		/*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;*/
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();
        String jobName = getString(R.string.rapi_doc) + "PDF";
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setMediaSize(PrintAttributes.MediaSize.NA_LETTER);
        PrintJob printJob = printManager.print(jobName, printAdapter, builder.build());

        if(printJob.isCompleted()){
            Toast.makeText(getApplicationContext(), "PDF Exitoso", Toast.LENGTH_LONG).show();
        }
        else if(printJob.isFailed()){
            Toast.makeText(getApplicationContext(), "PDF Erroneo", Toast.LENGTH_LONG).show();
        }
        // Save the job object for later status checking
    }

    public void addListenerOnButton() {
        buttonPDF = (Button) findViewById(R.id.button4RS);

        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWebPagePrint(webViewDataRapi);
            }
        });

    }
}
