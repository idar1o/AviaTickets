package com.example.myappavia.presentation.screens.orderscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappavia.R;
import com.example.myappavia.databinding.FragmentOrdersBinding;
import com.example.myappavia.databinding.FragmentPassengersBinding;

public class OrdersFragment extends Fragment {

    FragmentOrdersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater);
        binding.button3.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_ordersFragment_to_detailOrderFragment);
        });
        return binding.getRoot();
    }
}