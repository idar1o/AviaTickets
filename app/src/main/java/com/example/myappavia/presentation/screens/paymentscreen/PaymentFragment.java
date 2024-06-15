package com.example.myappavia.presentation.screens.paymentscreen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myappavia.R;
import com.example.myappavia.databinding.FragmentPaymentBinding;
import com.example.myappavia.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class PaymentFragment extends Fragment {

    private PaymentViewModel mViewModel;
    FragmentPaymentBinding binding;
    ArrayList<String> results;

    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            results = arguments.getStringArrayList("ForPayment");
        }
        binding.nametxt.setText(results.get(0));
        binding.pasport.setText(results.get(4));
        binding.pasport.setText(results.get(1));
        binding.numberDoctxt.setText(results.get(3));
        binding.telephonTxt.setText(results.get(2));
        binding.email.setText(results.get(6));
        binding.price.setText(results.get(5));

        binding.buttonPay.setOnClickListener(view ->{
            if (!binding.checkBox.isChecked()){
                Toast.makeText(getContext(), "Подтвердите ознакомление с правилами", Toast.LENGTH_LONG);
            }else{
                Navigation.findNavController(requireView()).navigate(R.id.action_paymentFragment_to_searchFragment);
            }
        });
    }

}