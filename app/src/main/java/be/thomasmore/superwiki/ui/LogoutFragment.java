package be.thomasmore.superwiki.ui;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.thomasmore.superwiki.LoginActivity;
import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.RegisterActivity;
import be.thomasmore.superwiki.helper.DatabaseHelper;

public class LogoutFragment extends Fragment {

    DatabaseHelper db;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logout_fragment, container, false);
        db = new DatabaseHelper(getContext());
        logout();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void logout(){
        SharedPreferences settings = getActivity().getSharedPreferences("TokenPrefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.clear();
        editor.commit();
        db.deleteDatabase();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

}
