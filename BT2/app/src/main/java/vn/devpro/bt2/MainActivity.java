package vn.devpro.bt2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        loadFragment(new FragmentA());
        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment fragment = null;

            if(item.getItemId()==R.id.tabA)
                fragment = new FragmentA();

            if(item.getItemId()==R.id.tabB)
                fragment = new FragmentB();

            if(item.getItemId()==R.id.tabC)
                fragment = new FragmentC();

            if(item.getItemId()==R.id.tabD)
                fragment = new FragmentD();

            loadFragment(fragment);

            return true;
        });
    }

    private void loadFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();

    }
}