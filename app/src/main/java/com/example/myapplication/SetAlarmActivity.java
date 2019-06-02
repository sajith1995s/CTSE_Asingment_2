package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class SetAlarmActivity extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener{

//    private DatePickerDialog tp_time;

    private TimePicker tp_time;
    private EditText et_name;
    private Spinner s_tone;
    private String format;


    private AlarmDialogListner listner;

    MediaPlayer mediaPlayer = new MediaPlayer();



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        Date currentTime = (Date) Calendar.getInstance().getTime();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.set_alarm, null);


        builder.setView(view)
                .setTitle("Add Alarm")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                    }
                })

                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = et_name.getText().toString();
                        int hours =  tp_time.getCurrentHour();
                        int minutes = tp_time.getCurrentMinute();
                        String tone = s_tone.getSelectedItem().toString();


                        String hour = tp_time.getCurrentHour().toString();
                        String minute = tp_time.getCurrentMinute().toString();

                        if (hours < 10) {
                            hour = "0".concat(hours + "");
                        }
                        if (minutes < 10) {
                            minute = "0".concat(minutes + "");
                        }

                        String time = hour.concat(":").concat(minute);

                        if(tone.equals("Select Tone")) {
                            tone = "Tone-1";  // set to default tone
                        }

                        listner.addAlarms(name, tone, time, true);
                        mediaPlayer.stop();
                    }
                });

        et_name = view.findViewById(R.id.et_name);
        tp_time = view.findViewById(R.id.timePicker);
        s_tone = view.findViewById(R.id.select_tone);

        s_tone.setOnItemSelectedListener(this);

        return builder.create();


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner = (AlarmDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String tone = s_tone.getSelectedItem().toString();

        if(tone.equals("Tone-1")){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.tone_1);
            mediaPlayer.start();
        }

        else if(tone.equals("Tone-2")){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.tone_2);
            mediaPlayer.start();
        }

        else if(tone.equals("Tone-3")){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.tone_3);
            mediaPlayer.start();
        }

        else if(tone.equals("Tone-4")){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.tone_4);
            mediaPlayer.start();
        }

        else if(tone.equals("Tone-5")){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.tone_5);
            mediaPlayer.start();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface AlarmDialogListner {
        void addAlarms(String name, String tone, String time, boolean isActive);
    }


    private void toastMessage(String message){
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }


}

