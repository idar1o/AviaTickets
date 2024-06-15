package com.example.myappavia.presentation.screens.ticketsfoundscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.myappavia.MainActivity;
import com.example.myappavia.R;
import com.example.myappavia.data.API.AviaAPI;
import com.example.myappavia.data.API.IATAAPI;
import com.example.myappavia.data.API.InitAviaAPI;
import com.example.myappavia.data.API.InitIATAAPI;
import com.example.myappavia.data.API.ModelTicket.FlightModel;
import com.example.myappavia.data.API.ModelTicket.ResponseFlightModel;
import com.example.myappavia.data.API.ModelTicket.ResponseFlightModelReturn;
import com.example.myappavia.data.API.ModelTicket.ResponseIATAs;
import com.example.myappavia.databinding.FragmentTicketsFoundBinding;
import com.example.myappavia.presentation.screens.ticketsfoundscreen.adapter.AdapterTicketsFound;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketsFoundFragment extends Fragment {

    private TicketsFoundViewModel mViewModel;
    private FragmentTicketsFoundBinding binding;
    private RecyclerView rcView;
    private AdapterTicketsFound adapter;
    private ArrayList<String> results;
    ResponseIATAs responseIATAs;

    private AdapterView.OnItemSelectedListener mListener;
    public static TicketsFoundFragment newInstance() {
        return new TicketsFoundFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTicketsFoundBinding.inflate(inflater, container, false);

        init();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(  view , savedInstanceState);

    }

    private void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            results = arguments.getStringArrayList("FlightData");
        }

        String startDate = convertDateFormat(results.get(2));
        binding.dateTxt.setText(startDate);
        binding.countPassTxt.setText("на "+results.get(4));
//        String startDate = "2023-05-21";

        String returnDate = convertDateFormat(results.get(3));




        rcView = binding.rcViewFoundTickets;


        IATAAPI iataapi = InitIATAAPI.createService();
        String request ="Из "+results.get(0)+" в "+results.get(1);
        binding.TitleCitiestxt.setText(results.get(0)+"-"+results.get(1));


        iataapi.getIATAs(request).enqueue(new Callback<ResponseIATAs>() {
            @Override
            public void onResponse(Call<ResponseIATAs> call, Response<ResponseIATAs> response) {

                responseIATAs= response.body();
                if (responseIATAs !=null){

                    AviaAPI aviaApi2 = InitAviaAPI.createService();
                    if (results.get(3).equals("Обратно")) {
                        Log.d("LOL", results.get(3)+" foundedTicketsDeparture");

//                    Log.d("LOL", responseIATAs.getOrigin().getIata()+"   "+)
                        aviaApi2.foundedTicketsDeparture(responseIATAs.getOrigin().getIata(), responseIATAs.getDestination().getIata(), startDate, "price").enqueue(new Callback<ResponseFlightModel>() {
                            @Override
                            public void onResponse(Call<ResponseFlightModel> call, Response<ResponseFlightModel> response) {
                                if (response.body() != null) {
                                    ArrayList<FlightModel> flightModelArrayList = response.body().getData();
                                    binding.countPassTxt.setText(String.valueOf(flightModelArrayList.size())+ "предложений");
                                    adapter = new AdapterTicketsFound(flightModelArrayList, results.get(0), results.get(1));
                                    adapter.setOnItemClickListener(new AdapterTicketsFound.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(FlightModel item) {
                                            Bundle bundle = new Bundle();
                                            ArrayList<String> arr = new ArrayList<>();
                                            arr.add(item.getTransfers());
                                            arr.add(item.getDuration_to());
                                            arr.add(item.getDeparture_at());
                                            arr.add(results.get(0));
                                            arr.add(item.getAirline() + "-" + item.getFlight_number());
                                            arr.add(item.getLink());
                                            arr.add(results.get(1));
                                            arr.add(item.getOrigin());
                                            arr.add(item.getDestination());
                                            arr.add(results.get(4));
                                            arr.add(item.getPrice());
                                            bundle.putStringArrayList("TicketData", arr);
                                            Navigation.findNavController(requireView()).navigate(R.id.action_ticketsFoundFragment_to_flightDetailFragment, bundle);
                                        }
                                    });
                                    rcView.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseFlightModel> call, Throwable t) {
                                if (t instanceof IOException) {
                                    Log.d("LOL", "ошибка сети");
                                } else {
                                    Log.d("LOL", "ошибка разбора данных или сервера");
                                }
                            }
                        });
                    }else{
                        Log.d("LOL", results.get(3)+" foundedTicketsReturn");
                        aviaApi2.foundedTicketsReturn(responseIATAs.getOrigin().getIata(), responseIATAs.getDestination().getIata(), startDate, returnDate, "price").enqueue(new Callback<ResponseFlightModel>() {
                            @Override
                            public void onResponse(Call<ResponseFlightModel> call, Response<ResponseFlightModel> response) {
                                if (response.body() != null) {
                                    ArrayList<FlightModel> flightModelArrayList = response.body().getData();
                                    binding.countPassTxt.setText(String.valueOf(flightModelArrayList.size())+ "предложений");
                                    adapter = new AdapterTicketsFound(flightModelArrayList, results.get(0), results.get(1));
                                    adapter.setOnItemClickListener(new AdapterTicketsFound.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(FlightModel item) {
                                            Bundle bundle = new Bundle();
                                            ArrayList<String> arr = new ArrayList<>();
                                            arr.add(item.getTransfers());
                                            arr.add(item.getDuration_to());
                                            arr.add(item.getDeparture_at());
                                            arr.add(results.get(0));
                                            arr.add(item.getAirline() + "-" + item.getFlight_number());
                                            arr.add(item.getLink());
                                            arr.add(results.get(1));
                                            arr.add(item.getOrigin_airport());
                                            arr.add(item.getDestination_airport());
                                            arr.add(results.get(4));
                                            arr.add(item.getReturn_at());
                                            arr.add(item.getDuration_back());
                                            arr.add(item.getPrice());

                                            bundle.putStringArrayList("TicketData", arr);
                                            Navigation.findNavController(requireView()).navigate(R.id.action_ticketsFoundFragment_to_flightDetailFragment, bundle);
                                        }
                                    });
                                    rcView.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseFlightModel> call, Throwable t) {
                                if (t instanceof IOException) {
                                    Log.d("LOL", "ошибка сети");
                                } else {
                                    Log.d("LOL", "ошибка разбора данных или сервера");
                                }
                            }
                        });
                    }
//
                }

            }

            @Override
            public void onFailure(Call<ResponseIATAs> call, Throwable t) {
                Log.d("LOL1", "III onFailure");
                if (t instanceof IOException){
                    Log.d("LOL", "ошибка сети");
                }else{
                    Log.d("LOL", "ошибка разбора данных или сервера");
                }
            }
        });






    }
    public String convertDateFormat(String inputDate) {
        String outputDate = "";

        // Определяем форматы входной и выходной даты
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Преобразуем входную строку в объект Date
            Date date = inputFormat.parse(inputDate);

            // Преобразуем объект Date в строку в выходном формате
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }

}