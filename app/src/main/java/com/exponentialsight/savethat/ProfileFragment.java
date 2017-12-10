package com.exponentialsight.savethat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "ProfileFrag";
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount gsa;
    private TextView userName;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(),gso);
        gsa = GoogleSignIn.getLastSignedInAccount(getContext());
        userName = view.findViewById(R.id.name);
        userName.setText(getString(R.string.signed_in_fmt, gsa.getDisplayName()));
        view.findViewById(R.id.sign_out_button).setOnClickListener(this);
        view.findViewById(R.id.disconnect_button).setOnClickListener(this);

        return view;
    }

    private void signOut() {
        mGoogleSignInClient.signOut();
        LoginActivity loginActivity = new LoginActivity();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    private void disconnect() {
        mGoogleSignInClient.revokeAccess();
        LoginActivity loginActivity = new LoginActivity();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                disconnect();
                break;
        }
    }
}
