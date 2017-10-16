package auto.javax.valeriano.autosrobadosmexico;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by israe on 06/10/2017.
 */

public class OurViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);

        return true; // as mentioned in below notes, for your case., you do 'return false'
    }

}