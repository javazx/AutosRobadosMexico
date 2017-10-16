package auto.javax.valeriano.autosrobadosmexico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class VerificentroMX extends AppCompatActivity {

    private WebView webViewMX;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificentro_mx);

        mAdView = (AdView) findViewById(R.id.adViewVMX);
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

        webViewMX = (WebView) findViewById(R.id.webViewMX);
        webViewMX.getSettings().setJavaScriptEnabled(true);
        webViewMX.getSettings().setAllowContentAccess(true);
        webViewMX.getSettings().setLoadWithOverviewMode(true);
        webViewMX.getSettings().setUseWideViewPort(true);
        webViewMX.getSettings().setSupportZoom(true);
        webViewMX.getSettings().setAllowFileAccess(true);
        webViewMX.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webViewMX.getSettings().setBuiltInZoomControls(true);
        webViewMX.setEnabled(true);
        webViewMX.setWebViewClient(new OurViewClient());
        try {
            webViewMX.loadUrl("http://sma.edomex.gob.mx/directorio_de_verificentros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
