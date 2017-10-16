package auto.javax.valeriano.autosrobadosmexico;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    ImageButton imageButtonRepuve;
    ImageButton imageButtonRapi;
    ImageButton imageButtonAmis;
    ImageButton imageButtonHoyNo;
    boolean estatus = false;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load an ad into the AdMob banner view.
        MobileAds.initialize(this, "ca-app-pub-7927185138091691~2124666089");
        mAdView = (AdView) findViewById(R.id.adView);
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

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        imageButtonRepuve = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonRapi = (ImageButton) findViewById(R.id.imageButton5);
        imageButtonAmis = (ImageButton) findViewById(R.id.imageButton3);
        imageButtonHoyNo = (ImageButton) findViewById(R.id.imageButton6);

        imageButtonRepuve.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "REPUVE!", Toast.LENGTH_SHORT).show();
                estatus = isOnline();
                if(estatus){
                    Intent i = new Intent(MainActivity.this, RepuveActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Verifica tu Conexion a Internet!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imageButtonRapi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "RAPI!", Toast.LENGTH_SHORT).show();
                estatus = isOnline();
                if(estatus){
                    Intent i = new Intent(MainActivity.this, RapiMainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Verifica tu Conexion a Internet!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imageButtonAmis.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "AMIS!", Toast.LENGTH_SHORT).show();
                estatus = isOnline();
                if(estatus){
                    Intent i = new Intent(MainActivity.this, AmisMainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Verifica tu Conexion a Internet!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imageButtonHoyNo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "Hoy no Circula!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Tools_TabActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = cm.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }
}
