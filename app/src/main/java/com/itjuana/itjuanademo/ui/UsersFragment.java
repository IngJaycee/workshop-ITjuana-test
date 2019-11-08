package com.itjuana.itjuanademo.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itjuana.itjuanademo.R;
import com.itjuana.itjuanademo.ui.adapter.UserAdapter;
import com.itjuana.itjuanademo.viewmodel.MainViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsersFragment extends Fragment {
    private static final String TAG = "UsersFragment";
    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;

    private UserAdapter userAdapter;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);

        userAdapter = new UserAdapter(new ArrayList<>(), userEntity -> {
            Toast.makeText(getContext(), userEntity.getDescription(), Toast.LENGTH_SHORT).show();
        });

        recyclerList.setAdapter(userAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(requireContext()));
        Disposable disposable = mainViewModel.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userEntities -> {
                    Log.d(TAG, "onCreateView: " + userEntities.toString());
                    userAdapter.setData(userEntities);
                }, Throwable::printStackTrace);
        return view;
    }

}
