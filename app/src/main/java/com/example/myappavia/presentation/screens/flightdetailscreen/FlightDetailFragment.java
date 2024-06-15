package com.example.myappavia.presentation.screens.flightdetailscreen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappavia.R;
import com.example.myappavia.data.API.AviaAPI;
import com.example.myappavia.data.API.IATAAPI;
import com.example.myappavia.data.API.InitAviaAPI;
import com.example.myappavia.data.API.InitIATAAPI;
import com.example.myappavia.data.API.ModelTicket.GetNameCity;
import com.example.myappavia.data.API.ModelTicket.ResponseIATAs;
import com.example.myappavia.databinding.FragmentFlightDetailBinding;
import com.example.myappavia.databinding.FragmentSearchBinding;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightDetailFragment extends Fragment {

    private FlightDetailViewModel mViewModel;
    private FragmentFlightDetailBinding binding;
    private ArrayList<String> results;


    public static FlightDetailFragment newInstance() {
        return new FlightDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFlightDetailBinding.inflate(inflater, container, false);
        init();
        return  binding.getRoot();
    }

    private void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            results = arguments.getStringArrayList("TicketData");
        }

        if (Integer.parseInt(results.get(0))>0){
            binding.oneFlyTxt.setText("c перелётами");
            int parseInt = Integer.parseInt(results.get(0));
            AviaAPI iataapi = InitAviaAPI.createService();

            iataapi.getNameCityWithIATA().enqueue(new Callback<ArrayList<GetNameCity>>() {
                @Override
                public void onResponse(Call<ArrayList<GetNameCity>> call, Response<ArrayList<GetNameCity>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<GetNameCity> cities = response.body();

                        ArrayList<String> res = getUppercaseStringBetweenStrings(results.get(5), results.get(7), results.get(8));
                        String strResss = "";
                        for (String code: res) {
                            String city = findCityNameByIataCode(cities, code);
                            strResss=strResss+city+",";

                        }
                        binding.durchStadtreturn.setText(strResss);
                        binding.durchStadt.setText(strResss);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<GetNameCity>> call, Throwable t) {
                    if (t instanceof IOException) {
                        Log.d("LOL", "ошибка сети");
                    } else {
                        Log.d("LOL", "ошибка разбора данных или сервера");
                    }
                }
            });
            //надо перевести iata коды
        }else{
            binding.linearLayoutPerelet.setVisibility(View.GONE);
        }
        binding.totalTimetxt.setText(convertMinutesToHours(Integer.parseInt(results.get(1))));
        binding.departureAttxt.setText(formatDateTime(results.get(2)));
        binding.originTxt.setText(results.get(3));
        binding.raceFlightTxt.setText(results.get(4));
        binding.finishTimeTxt.setText(addMinutesToDateTime(formatDateTime(results.get(2)), Integer.parseInt(results.get(1))));
        binding.destinationTxt.setText(results.get(6));

        if (results.size()>11){

            if (Integer.parseInt(results.get(0))>0){
                binding.oneFlyTxtreturn.setText("c перелётами");

            }else{
                binding.linearLayoutPereletObratno.setVisibility(View.GONE);
            }
            binding.totalTimetxtreturn.setText(convertMinutesToHours(Integer.parseInt(results.get(1))));
            binding.departureAttxtreturn.setText(formatDateTime(results.get(10)));
            binding.originTxtreturn.setText(results.get(6));
            binding.raceFlightTxtreturn.setText(results.get(4));
            binding.finishTimeTxtreturn.setText(addMinutesToDateTime(formatDateTime(results.get(10)), Integer.parseInt(results.get(11))));
            binding.destinationTxtreturn.setText(results.get(3));
        }else binding.linearLayoutObratno.setVisibility(View.GONE);

        String number = results.get(9).replaceAll("\\D+", "");
        int passengers = Integer.parseInt(number);

        binding.priceTicket.setText(String.valueOf((int) Math.floor(Integer.parseInt(results.get(12)) * 0.91 * passengers)) + " cом");
        binding.stickyButton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            ArrayList<String> arr = new ArrayList<>();
            arr.add(String.valueOf(passengers));
            arr.add(binding.priceTicket.getText().toString());
            arr.add(binding.departureAttxt.getText().toString());
            arr.add(binding.finishTimeTxt.getText().toString());
            arr.add(binding.originTxt.getText().toString());
            arr.add(binding.destinationTxt.getText().toString());
            arr.add(binding.durchStadt.getText().toString());

            bundle.putStringArrayList("ForPassportData",arr);
            Navigation.findNavController(requireView()).navigate(R.id.action_flightDetailFragment_to_dataVerificationFragment, bundle);
        });

    }
    private String findCityNameByIataCode(List<GetNameCity> cities, String iataCode) {
        for (GetNameCity city : cities) {
            if (city.getCode().equals(iataCode)) {
                return city.getName();
            }
        }
        return null;
    }


    public String addMinutesToDateTime(String dateTimeString, int minutes) {
        // Исходный формат даты и времени
        String inputFormat = "dd MMMM, HH:mm";
        // Локаль для локализации формата даты и времени
        Locale locale = Locale.getDefault();

        try {
            // Создаем объект SimpleDateFormat с указанным входным форматом и локалью
            SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat, locale);

            // Парсим строку с датой и временем в объект Date
            Date dateTime = dateFormat.parse(dateTimeString);

            // Создаем объект Calendar и устанавливаем дату и время
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTime);

            // Добавляем указанное количество минут
            calendar.add(Calendar.MINUTE, minutes);

            // Получаем новую дату и время
            Date newDateTime = calendar.getTime();

            // Форматируем новую дату и время в строку с исходным форматом
            return dateFormat.format(newDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Возвращаем null в случае ошибки парсинга
        }
    }

    public static ArrayList<String> getUppercaseStringBetweenStrings(String text, String startString, String endString) {
        String pattern = "(?<=" + startString + ")[A-Z]+(?=" + endString + ")";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(text);

        if (matcher.find()) {
            String textres =  matcher.group();
            ArrayList<String> chunks = new ArrayList<>();

            for (int i = 0; i < textres.length(); i += 3) {
                int endIndex = Math.min(i + 3, textres.length());
                String chunk = textres.substring(i, endIndex);
                chunks.add(chunk);
            }

            return chunks;
        } else {
            return new ArrayList<String>(Arrays.asList("MOW", "MOW"));
        }
    }

    public String formatDateTime(String dateTimeString) {
        // Исходный формат даты и времени
        String inputFormat = "yyyy-MM-dd'T'HH:mm:ssXXX";
        // Желаемый формат даты и времени
        String outputFormat = "dd MMMM, HH:mm";
        // Локаль для локализации формата вывода
        Locale locale = Locale.getDefault();

        try {
            // Создаем объект SimpleDateFormat с указанным входным форматом
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, locale);
            // Устанавливаем временную зону, если необходимо
            inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            // Парсим строку с датой и временем в объект Date
            Date dateTime = inputDateFormat.parse(dateTimeString);

            // Создаем объект SimpleDateFormat с указанным выходным форматом
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat, locale);
            // Устанавливаем временную зону, если необходимо
            outputDateFormat.setTimeZone(TimeZone.getDefault());

            // Форматируем объект Date в строку с новым форматом
            return outputDateFormat.format(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Возвращаем null в случае ошибки парсинга
        }
    }

    public String convertMinutesToHours(int minutes) {
        int hours = minutes / 60; // Получаем количество полных часов
        int remainingMinutes = minutes % 60; // Получаем оставшиеся минуты

        String hoursText = (hours == 1) ? "час" : "часа";
        String minutesText = (remainingMinutes == 1) ? "минута" : "минуты";

        if (hours > 0 && remainingMinutes > 0) {
            return String.format("%d %s %d %s", hours, hoursText, remainingMinutes, minutesText);
        } else if (hours > 0) {
            return String.format("%d %s", hours, hoursText);
        } else if (remainingMinutes > 0) {
            return String.format("%d %s", remainingMinutes, minutesText);
        } else {
            return "0 минут";
        }
    }


}