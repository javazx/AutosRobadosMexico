package auto.javax.valeriano.autosrobadosmexico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class RepuveActivity extends AppCompatActivity {

    Button button;
    Button limpiar;
    EditText placa;
    EditText capcha;
    ImageView img;
    private WebView webView;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repuve);
        mAdView = (AdView) findViewById(R.id.adViewRepuve);
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
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www2.repuve.gob.mx:8080/ciudadania/jcaptcha");
        //img = (ImageView)findViewById(R.id.imageViewX2);
        //Picasso.with(this).load("http://www2.repuve.gob.mx:8080/ciudadania/jcaptcha").resize(250,150).centerCrop().into(img);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.button2);
        limpiar  = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), RepuveShowActivity.class);
                placa = (EditText)findViewById(R.id.editText);
                capcha = (EditText)findViewById(R.id.editText2);
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www2.repuve.gob.mx:8080/ciudadania/servletconsulta?placa="
                //        + placa.getText() + "&" +"captcha=" + capcha.getText()));
                //startActivity(browserIntent);
                if(placa.getText().toString().length() < 5){
                    Toast.makeText(RepuveActivity.this, "Placa o NIV Invalido!", Toast.LENGTH_SHORT).show();
                }else if(capcha.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(RepuveActivity.this, "Ingrese el Texto de la Imagen Correctamente!", Toast.LENGTH_SHORT).show();
                }else{
                    intent.putExtra("placa", placa.getText().toString());
                    intent.putExtra("captcha", capcha.getText().toString());
                    startActivity(intent);
                }
            }

        });

        limpiar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                placa = (EditText)findViewById(R.id.editText);
                capcha = (EditText)findViewById(R.id.editText2);
                //img.setImageBitmap(null);
                //Picasso.with(RepuveActivity.this).invalidate("http://www2.repuve.gob.mx:8080/ciudadania/jcaptcha");
                //Picasso.with(RepuveActivity.this).load("http://www2.repuve.gob.mx:8080/ciudadania/jcaptcha").resize(250,150).centerCrop().into(img);
                placa.setText("");
                capcha.setText("");
                webView.loadUrl("http://www2.repuve.gob.mx:8080/ciudadania/jcaptcha");
            }

        });

    }

}