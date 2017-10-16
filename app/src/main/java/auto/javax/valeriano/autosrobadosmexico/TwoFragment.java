package auto.javax.valeriano.autosrobadosmexico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TwoFragment extends Fragment{

    Button buttonDF;
    Button buttonMX;

    public TwoFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        buttonDF = (Button)rootView.findViewById(R.id.buttonDF);
        buttonMX = (Button)rootView.findViewById(R.id.buttonMX);

        buttonDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Click","Clickkk");
                Intent intent = new Intent(getActivity(), VerificentroDF.class);
                startActivity(intent);
            }
        });

        buttonMX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Click","Clickkk");
                Intent intent = new Intent(getActivity(), VerificentroMX.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
