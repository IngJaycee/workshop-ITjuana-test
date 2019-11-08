package com.itjuana.itjuanademo.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.itjuana.itjuanademo.R;
import com.itjuana.itjuanademo.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class AddNewMessageDialog extends DialogFragment {

    @BindView(R.id.btnSend)
    Button btnSend;

    @BindView(R.id.btnCancel)
    Button btnCancel;

    @BindView(R.id.etMessage)
    EditText etMessage;

    private MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_message, container, false);
        ButterKnife.bind(this, view);
        btnCancel.setOnClickListener(v -> {
            dismiss();
        });

        btnSend.setOnClickListener(v -> {
            mainViewModel.sendMessage(etMessage.getText().toString());
            dismiss();

        });

        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSend.setEnabled(s.toString().trim().length() >= 5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}