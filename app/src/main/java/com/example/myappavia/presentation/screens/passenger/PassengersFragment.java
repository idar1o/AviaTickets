package com.example.myappavia.presentation.screens.passenger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappavia.R;
import com.example.myappavia.databinding.FragmentPassengersBinding;

public class PassengersFragment extends Fragment {

    private FragmentPassengersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPassengersBinding.inflate(inflater);
        binding.button123.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_passengersFragment_to_detailPassengersFragment);
        });
        return binding.getRoot();
    }
}