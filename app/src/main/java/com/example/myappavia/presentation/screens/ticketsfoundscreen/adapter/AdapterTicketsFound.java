package com.example.myappavia.presentation.screens.ticketsfoundscreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappavia.R;
import com.example.myappavia.data.API.ModelTicket.FlightModel;
import com.example.myappavia.databinding.FlightsFoundItemsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AdapterTicketsFound extends RecyclerView.Adapter<AdapterTicketsFound.MyViewHolder> {


    private static ArrayList<FlightModel> flightModelArrayList;
    private static OnItemClickListener listener;
    private String origin, destination;

    public interface OnItemClickListener {
        void onItemClick(FlightModel item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AdapterTicketsFound(ArrayList<FlightModel> flightModelArrayList, String origin, String destination) {

        AdapterTicketsFound.flightModelArrayList = flightModelArrayList;
        this.origin = origin;
        this.destination = destination;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flights_found_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTicketsFound.MyViewHolder holder, int position) {
        FlightModel flightModel = flightModelArrayList.get(position);
        holder.startPlace.setText(origin);
        holder.startTime.setText(formatDateTime(flightModel.getDeparture_at()));
        holder.ticketCost.setText(String.valueOf((int) Math.floor(Integer.parseInt(flightModel.getPrice()) * 0.91)) + "cом");
        holder.finishPlace.setText(destination);
        holder.finishTime.setText(addMinutesToDateTime(formatDateTime(flightModel.getDeparture_at()), Integer.parseInt(flightModel.getDuration_to())));
        holder.totalTime.setText(convertMinutesToHours(Integer.parseInt(flightModel.getDuration_to())));

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

    @Override
    public int getItemCount() {
        return flightModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView startPlace;
        TextView startTime;
        TextView ticketCost;
        TextView finishPlace;
        TextView finishTime;
        TextView totalTime;

        public MyViewHolder(@NonNull View Itemview) {
            super(Itemview);
            startPlace = Itemview.findViewById(R.id.start_place);
            startTime = Itemview.findViewById(R.id.start_time);
            ticketCost = Itemview.findViewById(R.id.ticket_cost);
            finishPlace = Itemview.findViewById(R.id.finish_place);
            totalTime = Itemview.findViewById(R.id.total_time);
            finishTime = Itemview.findViewById(R.id.finish_time);


            Itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(flightModelArrayList.get(position));
                    }
                }
            });


        }

    }
}
