package rosita.aliffia.rekomendasibuku.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rosita.aliffia.rekomendasibuku.fragment.BookFragment;
import rosita.aliffia.rekomendasibuku.fragment.HomeFragment;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.fragment.RecommendFragment;
import rosita.aliffia.rekomendasibuku.fragment.UserFragment;
import rosita.aliffia.rekomendasibuku.adapter.ViewPagerAdapter;
import rosita.aliffia.rekomendasibuku.preference.AppPreference;
import rosita.aliffia.rekomendasibuku.preference.UserModel;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private ViewPager viewPager;
    private MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        bottomNav = findViewById(R.id.bottom_nav);

        setupViewPager(viewPager);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_book:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_rekomendasi:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_user:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNav.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNav.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new BookFragment());
        adapter.addFragment(new RecommendFragment());
        adapter.addFragment(new UserFragment() );
        viewPager.setAdapter(adapter);
    }
}
