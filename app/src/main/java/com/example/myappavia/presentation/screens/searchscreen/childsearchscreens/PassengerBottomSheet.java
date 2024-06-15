package com.example.myappavia.presentation.screens.searchscreen.childsearchscreens;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappavia.databinding.BottomSheetLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PassengerBottomSheet extends BottomSheetDialogFragment {
    private BottomSheetLayoutBinding binding;
    private int adultCount = 1;
    private int childCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Используем ViewBinding для раздувания макета Bottom Sheet
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false);

        buttonsOnClick();

        return binding.getRoot();
    }

    private void buttonsOnClick() {
        binding.adultcountTV.setText(String.valueOf(adultCount));
        binding.closeBtn.setOnClickListener(view ->dismiss());
        //adult count change buttons
        binding.adultplusbtn.setOnClickListener(view ->{
            adultCount+=1;
            binding.adultcountTV.setText(String.valueOf(adultCount));
        });
        binding.adultminusbtn.setOnClickListener(view ->{
            if (adultCount==1){
                adultCount-=0;
            }else{adultCount-=1;}
            binding.adultcountTV.setText(String.valueOf(adultCount));

        });
        //child count change buttons
        binding.childplusbtn.setOnClickListener(view ->{
            childCount+=1;
            binding.childcountTV.setText(String.valueOf(childCount));
        });
        binding.childminusbtn.setOnClickListener(view ->{
            if (childCount==0){
                childCount = 0;
            }else{childCount-=1;}
            binding.childcountTV.setText(String.valueOf(childCount));
        });
        //ready button
        binding.readyBtn.setOnClickListener(view ->{
            int[] data = new int[]{adultCount, childCount};
            Bundle bundle = new Bundle();
            bundle.putIntArray("PassengersData",data);
            getParentFragmentManager().setFragmentResult("datafrombottomsheet",bundle);
            dismiss();

        });
    }
}
