package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniellopes.treinamento.trabalhoartigodaves.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtigosEventoFragment extends Fragment {


    public ArtigosEventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artigos_evento, container, false);
        bind(view);

        return view;
    }

    private void bind(View view) {

    }


}
