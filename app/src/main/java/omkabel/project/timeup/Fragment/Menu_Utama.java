package omkabel.project.timeup.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import omkabel.project.timeup.Features.Menu_ListJadwal;
import omkabel.project.timeup.Fragment.ui.home.AllAppFragment;
import omkabel.project.timeup.R;
import omkabel.project.timeup.Utils.AppLockConstants;


public class Menu_Utama extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        sharedPreferences = getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        Fragment f = AllAppFragment.newInstance(AppLockConstants.ALL_APPS);
//        fragmentManager.beginTransaction().replace(R.id.fragment_container, f).commit();


    }
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//       intent.addCategory(Intent.CATEGORY_HOME);
//       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//       startActivity(intent);
//
//    }

}