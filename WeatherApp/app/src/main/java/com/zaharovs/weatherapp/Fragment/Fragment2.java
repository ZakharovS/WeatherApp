package com.zaharovs.weatherapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zaharovs.weatherapp.Activity.MainActiviti;
import com.zaharovs.weatherapp.R;

public class Fragment2 extends Fragment {

    public static Fragment2 newInstance(int itemPosition) {
        Bundle args = new Bundle();
        args.putInt("itemPosition", itemPosition);
        Fragment2 fragment2 = new Fragment2();
        fragment2.setArguments(args);
        return fragment2;
    }

    public int getPosition() {
        return getArguments().getInt("itemPosition", 0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentContentView = inflater.inflate(R.layout.fragment_content, container, false);

        if (MainActiviti.data != null) {
            // DAY & MONTH
            TextView dayMonth = (TextView) fragmentContentView.findViewById(R.id.item_day_month);
            dayMonth.setText(MainActiviti.data.get(getPosition()).get("day_of_month") + " "
                    + MainActiviti.data.get(getPosition()).get("month") + ", "
                    + MainActiviti.data.get(getPosition()).get("day_of_week"));

            // ICON
            ImageView imageView = (ImageView) fragmentContentView.findViewById(R.id.item_icon);
            String icon = MainActiviti.data.get(getPosition()).get("weather_icon");
            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
            Picasso.with(getActivity()).load(iconUrl).into(imageView);

            // ICON DESCRIPTION
            TextView iconDescription = (TextView) fragmentContentView.findViewById(R.id.item_icon_description);
            iconDescription.setText(MainActiviti.data.get(getPosition()).get("time") + ", "
                    + MainActiviti.data.get(getPosition()).get("weather_description"));

            // TEMP MIN
            TextView tempMin = (TextView) fragmentContentView.findViewById(R.id.item_temp_min);
            tempMin.setText("Temp Min: " + MainActiviti.data.get(getPosition()).get("temp_min"));

            // TEMP MAX
            TextView tempMax = (TextView) fragmentContentView.findViewById(R.id.item_temp_max);
            tempMax.setText("Temp Max: " + MainActiviti.data.get(getPosition()).get("temp_max"));

            // WIND SPEED
            TextView windSpeed = (TextView) fragmentContentView.findViewById(R.id.item_wind_speed);
            windSpeed.setText("Wind Speed: " + MainActiviti.data.get(getPosition()).get("wind_speed"));

            // CLOUDS
            TextView cloudsAll = (TextView) fragmentContentView.findViewById(R.id.item_clouds_all);
            cloudsAll.setText("Cloudiness: " + MainActiviti.data.get(getPosition()).get("clouds_all"));

            // HUMIDITY
            TextView humidity = (TextView) fragmentContentView.findViewById(R.id.item_humidity);
            humidity.setText("Humidity: " + MainActiviti.data.get(getPosition()).get("humidity"));

            // PRESSURE
            TextView pressure = (TextView) fragmentContentView.findViewById(R.id.item_pressure);
            pressure.setText("Pressure: " + MainActiviti.data.get(getPosition()).get("pressure"));
        }

        return fragmentContentView;
    }
}