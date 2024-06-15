package com.example.myappavia.presentation.screens.dataverificationscreen;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.myappavia.R;

import com.example.myappavia.data.DB.DAO.MyDatabase;
import com.example.myappavia.data.DB.DAO.Passenger;
import com.example.myappavia.data.DB.DAO.Ticket;
import com.example.myappavia.data.DB.DAO.TicketDao;
import com.example.myappavia.data.DB.DAO.PassengerDAO;
import com.example.myappavia.databinding.FragmentDataVerificationBinding;

import java.util.ArrayList;

public class DataVerificationFragment extends Fragment {

    private DataVerificationViewModel mViewModel;
    private FragmentDataVerificationBinding binding;

    ArrayList<String> results;

    public static DataVerificationFragment newInstance() {
        return new DataVerificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDataVerificationBinding.inflate(inflater);
        init();

        return binding.getRoot();
    }

    private void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            results = arguments.getStringArrayList("ForPassportData");
        }
        binding.destinationTxt.setText(results.get(5));
        binding.finishTimeTxt.setText(results.get(3));
        binding.originTxt.setText(results.get(4));
        binding.priceTxt.setText(results.get(1));
        binding.startTimeTxt.setText(results.get(2));
        binding.passegersTxt.setText(results.get(0)+" пассажиров");

        binding.continueBtn.setOnClickListener(view -> {



            String name = binding.editTextTextPersonName.getText().toString();
            String lastname = binding.editTextTextPersonLastName.getText().toString();
            String email = binding.editTextTextEmailAddress.getText().toString();
            String number_doc = binding.editTextDocNumber.getText().toString();
            String sex;
            if (binding.maleradioBtn.isChecked()) sex = binding.maleradioBtn.getText().toString();
            else sex = binding.femaleradioBtn.getText().toString();;
            String doc;
            String number = binding.editTextPhone.getText().toString();
            if (binding.radioBtnKR.isChecked()) doc= binding.radioBtnKR.getText().toString();
            else doc = binding.radioBtnZagran.getText().toString();



            Passenger passenger = new Passenger(name , lastname, number , email , sex, doc, number_doc);
            Ticket ticket = new Ticket(results.get(4),
                    results.get(5),
                    results.get(2),
                    results.get(3),
                    results.get(1),
                    Integer.parseInt(results.get(0)),
                    passenger.getPassID(),
                    results.get(6),
                    false);

            MyDatabase myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class,
                    "myDb").build();
            MyDatabase myDatabase2 = Room.databaseBuilder(getContext(), MyDatabase.class,
                    "myDb").build();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    myDatabase.passengerDAO().insertPassenger(passenger);

                }
            }).start();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    myDatabase2.ticketDao().insertTicket(ticket);
                }
            }).start();

            Bundle bundle = new Bundle();
            ArrayList<String> res = new ArrayList<>();
            res.add(name);
            res.add(lastname);
            res.add(String.valueOf(number));
            res.add(String.valueOf(number_doc));
            res.add(doc);
            res.add(results.get(1));
            res.add(email);

            bundle.putStringArrayList("ForPayment", res);
            Navigation.findNavController(requireView()).navigate(R.id.action_dataVerificationFragment_to_paymentFragment, bundle);

        });
    }
    public static Migration MiGRATION = new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter table 'Tickets' add column 'PassID'");
        }
    };
//    class InsertAsyncTask extends AsyncTask<Passenger, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Passenger... passengers) {
//
//            return null;
//        }
//    }



}