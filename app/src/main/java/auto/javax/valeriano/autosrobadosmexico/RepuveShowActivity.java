package auto.javax.valeriano.autosrobadosmexico;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class RepuveShowActivity extends AppCompatActivity {

    private WebView webViewData;
    private Button buttonPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repuve_show);

        String placa = getIntent().getStringExtra("placa");
        String captcha = getIntent().getStringExtra("captcha");

        webViewData = (WebView) findViewById(R.id.webView2);
        webViewData.getSettings().setJavaScriptEnabled(false);
        webViewData.getSettings().setAllowContentAccess(false);
        webViewData.getSettings().setLoadWithOverviewMode(true);
        webViewData.getSettings().setUseWideViewPort(true);
        webViewData.getSettings().setSupportZoom(true);
        webViewData.getSettings().setAllowFileAccess(false);
        webViewData.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webViewData.getSettings().setBuiltInZoomControls(true);
        webViewData.setEnabled(false);
        webViewData.setOnTouchListener(null);
        webViewData.loadUrl("http://www2.repuve.gob.mx:8080/ciudadania/servletconsulta?placa="
                + placa.toString() + "&" +"captcha=" + captcha.toString());

        addListenerOnButton();
    }

    public  void createWebPagePrint(WebView webView) {
		/*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;*/
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();
        String jobName = getString(R.string.amis_doc) + "_PDF";
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

    public void addListenerOnButton() {
        buttonPDF = (Button) findViewById(R.id.button4);

        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWebPagePrint(webViewData);
            }
        });

    }
}
