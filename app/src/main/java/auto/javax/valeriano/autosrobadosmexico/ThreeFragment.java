package auto.javax.valeriano.autosrobadosmexico;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class ThreeFragment extends Fragment{

    private WebView webViewData;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        webViewData = (WebView) rootView.findViewById(R.id.webView2CA);
        webViewData.getSettings().setJavaScriptEnabled(true);
        webViewData.getSettings().setAllowContentAccess(true);
        webViewData.getSettings().setLoadWithOverviewMode(true);
        webViewData.getSettings().setUseWideViewPort(true);
        webViewData.getSettings().setSupportZoom(true);
        webViewData.getSettings().setAllowFileAccess(true);
        webViewData.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webViewData.getSettings().setBuiltInZoomControls(true);
        webViewData.loadUrl("http://www.aire.cdmx.gob.mx/pronostico-aire/pronostico-calidad-aire.php");
        return rootView;
    }

}
