package com.example.myappavia.presentation.screens.searchscreen;

import static java.lang.String.valueOf;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.myappavia.R;
import com.example.myappavia.databinding.FragmentSearchBinding;
import com.example.myappavia.presentation.screens.searchscreen.childsearchscreens.PassengerBottomSheet;
import com.example.myappavia.presentation.screens.searchscreen.childsearchscreens.PointAFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class SearchFragment extends Fragment{

    private SearchViewModel mViewModel;
    private BottomSheetDialog bottomSheetDialog;
    FragmentSearchBinding binding;
    PointAFragment pointAFragment;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        init();
        getChildFragmentManager().setFragmentResultListener("datafromPointA", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                ArrayList<String> placeData= result.getStringArrayList("ItemData");
                String res= placeData.get(0);
                if (pointAFragment.getTitle().equals("Откуда")) binding.otkudabt.setText(res);
                else binding.kudabt.setText(res);

            }
        });
        getChildFragmentManager().setFragmentResultListener("datafrombottomsheet", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                int[] countPass= result.getIntArray("PassengersData");
                String res=String.valueOf(countPass[0] + countPass[1]);
                binding.passengerBnt.setText(res + " пассажиров");

            }
        });

        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void init() {
        binding.passengerBnt.setOnClickListener(view -> {
            showBottomSheet();
        });

        binding.otkudabt.setOnClickListener(view -> {
            pointAFragment = new PointAFragment("Откуда");
            pointAFragment.show(getChildFragmentManager(), pointAFragment.getTag());
        });

        binding.kudabt.setOnClickListener(view -> {
            pointAFragment = new PointAFragment("Куда");
            pointAFragment.show(getChildFragmentManager(), pointAFragment.getTag());
        });
        binding.tudaBtn.setOnClickListener(view -> {
            showDialog(binding.tudaBtn);

        });

        binding.obratnoBtn.setOnClickListener(view -> {
            showDialog(binding.obratnoBtn);
        });

        binding.floatingActionButton.setOnClickListener(view -> {
            if (!binding.kudabt.getText().toString().equals("Куда") && (!binding.otkudabt.getText().toString().equals("Откуда"))){
                String k1 = binding.otkudabt.getText().toString();
                String k2 = binding.kudabt.getText().toString();
                binding.kudabt.setText(k1);
                binding.otkudabt.setText(k2);
            }
        });
        binding.searchBtn.setOnClickListener(view ->{
            ArrayList<String> data = new ArrayList<>();
            String pointA= binding.otkudabt.getText().toString();
            String pointB= binding.kudabt.getText().toString();
            String dayFly= binding.tudaBtn.getText().toString();
            String dayArrived= binding.obratnoBtn.getText().toString();
            String countPass= binding.passengerBnt.getText().toString();
            data.add(pointA);
            data.add(pointB);
            data.add(dayFly);
            data.add(dayArrived);
            data.add(countPass);

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("FlightData", data);

            Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment_to_ticketsFoundFragment, bundle);
        });
    }

    private void showDialog(Button button) {
        DatePickerDialog datePicker = new DatePickerDialog(requireContext(),  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                button.setText(valueOf(day) + "/" + valueOf(month + 1) + "/" + valueOf(year));
            }
        },2023,5,9);
        datePicker.show();
    }

    private void showBottomSheet() {
        PassengerBottomSheet bottomSheet = new PassengerBottomSheet();
        bottomSheet.show(getChildFragmentManager(), bottomSheet.getTag());

    }


}