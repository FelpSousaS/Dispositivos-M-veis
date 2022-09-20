package br.quixada.ufc.appcomponents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import br.quixada.ufc.appcomponents.fragments.InicioFragment;
import br.quixada.ufc.appcomponents.fragments.MyMusicFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter fragmentStateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.viewpager2);

        viewPager2.setAdapter(fragmentStateAdapter = new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                        InicioFragment inicioFragment = new InicioFragment();
                        return  inicioFragment;
                    case 1:
                        MyMusicFragment myMusicFragment = new MyMusicFragment();
                        return myMusicFragment;
                    default:
                        return null;
                }
            }

            @Override
            public int getItemCount() {
                return tabLayout.getTabCount();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ajuda:
                startActivity(new Intent(this, Ajuda.class));
                break;
            case R.id.sobre:
                startActivity(new Intent(this,Sobre.class));
                break;
            case R.id.config :
                startActivity(new Intent(this, Configuracao.class));
        }
        return super.onOptionsItemSelected(item);
    }
}