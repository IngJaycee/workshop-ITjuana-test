package com.itjuana.itjuanademo.ui;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.itjuana.itjuanademo.R;
import com.itjuana.itjuanademo.common.OnItemClickListener;
import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.ui.adapter.ListAdapter;
import com.itjuana.itjuanademo.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListFragment extends Fragment {
    private View view;
    private static final String TAG = "ListFragment";

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;

    private ListAdapter listAdapter;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        handlingBack();
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        listAdapter = new ListAdapter(new ArrayList<>(), messageEntity -> {
            Toast.makeText(requireContext(), messageEntity.getBody(), Toast.LENGTH_SHORT).show();
        });
        recyclerList.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerList.setAdapter(listAdapter);
        recyclerList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
                outRect.left = 10;
                outRect.right = 10;
                outRect.top = 15 /*space / 2 */;
            }
        });

        Disposable disposable = mainViewModel.getAllMessages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(messageList -> {
                    Log.d(TAG, "onCreateView: " + messageList.toString());
                    listAdapter.setData(messageList);
                }, Throwable::printStackTrace);

        fab.setOnClickListener(v -> {
            writeMessage();
        });

        return view;
    }

    private void handlingBack() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                Log.d(TAG, "handleOnBackPressed: ");
                Snackbar snackbar = Snackbar.make(view, "do you want to exit?", Snackbar.LENGTH_LONG);
                snackbar.setAction("YES", v -> {
                    remove();
                    Navigation.findNavController(view).popBackStack(R.id.listFragment, true);
                    requireActivity().onBackPressed();
                });
                snackbar.show();

            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void writeMessage() {
//        mainViewModel.sendMessage(new MessageEntity("JC", "hola Soy GOKU", "Hola que hace, hahaahah"));
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_listFragment_to_addNewMessageDialog);
    }
}
