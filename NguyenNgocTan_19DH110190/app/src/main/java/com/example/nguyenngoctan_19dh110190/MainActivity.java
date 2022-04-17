package com.example.nguyenngoctan_19dh110190;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    TextView textCartItemCount;
    int mCartItemCount = 0;

    Menu mMenu;
    boolean flag = true;

    FirebaseDatabase fDatabase;
    FirebaseAuth fAuth;

    TextView tvFullName, tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fDatabase = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);


        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawer,
                toolbar,
                R.string.open,
                R.string.close);

        drawer.addDrawerListener(actionBarDrawerToggle);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.orderFragment, R.id.profileFragment, R.id.logoutFragment)
                .setOpenableLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View view = navigationView.getHeaderView(0);
        tvFullName = view.findViewById(R.id.tvFullName);
        tvEmail = view.findViewById(R.id.tvEmail);

        String userID = fAuth.getCurrentUser().getUid();

        fDatabase.getReference().child("users").child(userID).get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        user.setUserID(userID);
                        tvFullName.setText(user.getFirstname() + " " + user.getLastname());
                        tvEmail.setText(user.getEmail());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

}