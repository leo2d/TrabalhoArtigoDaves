package daniellopes.treinamento.trabalhoartigodaves.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daniellopes.treinamento.trabalhoartigodaves.Contracts.DrawerLocker;
import daniellopes.treinamento.trabalhoartigodaves.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment {

    private DrawerLocker drawerLocker;

    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        drawerLocker =(DrawerLocker)getActivity();

        drawerLocker.setDrawerLocked(true);

        return inflater.inflate(R.layout.fragment_principal, container, false);


    }


}
