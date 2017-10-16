package auto.javax.valeriano.autosrobadosmexico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class VerificentroDF extends AppCompatActivity {

    private WebView webViewDF;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificentro_df);

        mAdView = (AdView) findViewById(R.id.adViewVDF);
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

        webViewDF = (WebView) findViewById(R.id.webViewDF);
        webViewDF.getSettings().setJavaScriptEnabled(false);
        webViewDF.getSettings().setAllowContentAccess(false);
        webViewDF.getSettings().setLoadWithOverviewMode(true);
        webViewDF.getSettings().setUseWideViewPort(true);
        webViewDF.getSettings().setSupportZoom(true);
        webViewDF.getSettings().setAllowFileAccess(false);
        webViewDF.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webViewDF.getSettings().setBuiltInZoomControls(true);
        webViewDF.setEnabled(false);
        webViewDF.setOnTouchListener(null);
        webViewDF.setWebViewClient(new OurViewClient());
        try {
            webViewDF.loadUrl("http://verificentros.sedema.cdmx.gob.mx:8080/dvc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
