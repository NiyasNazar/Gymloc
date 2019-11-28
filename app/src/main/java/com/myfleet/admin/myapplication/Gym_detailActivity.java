package com.myfleet.admin.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Gym_detailActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @BindView(R.id.tv_gymname) TextView Gymname;
    @BindView(R.id.tv_gymloc) TextView gymlocation;
    @BindView(R.id.tv_gyregfees) TextView gymregfees;
    @BindView(R.id.tv_monthlyfees) TextView gymmontlyfees;
    @BindView(R.id.tv_gymname) TextView gymopeningtime;
    @BindView(R.id.tv_gymname) TextView gymclosingtime;
    @BindView(R.id.tv_gymname) TextView gymwebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);

        String abc = getIntent().getStringExtra("gymname");
        Toast.makeText(getApplicationContext(), "da" + abc, Toast.LENGTH_LONG).show();
        Button b = (Button) findViewById(R.id.b1);
        b.setText("GET DIRECTION");
Gymname.setText(abc);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(  9.991410, 76.290047);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));


    }
}
