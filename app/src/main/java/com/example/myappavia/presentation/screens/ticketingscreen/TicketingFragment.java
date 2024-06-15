package com.example.myappavia.presentation.screens.ticketingscreen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappavia.R;

public class TicketingFragment extends Fragment {

    private TicketingViewModel mViewModel;

    public static TicketingFragment newInstance() {
        return new TicketingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticketing, container, false);
    }

}