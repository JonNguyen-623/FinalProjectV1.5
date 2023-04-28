package edu.sjsu.android.finalprojectv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: let users add a new item to list
        //either a popup or new screen for the data they want to add
        findViewById(R.id.adding).setOnClickListener(v->{
            Toast.makeText(this, "Not Implemented Yet",
                    Toast.LENGTH_LONG).show();
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //TODO: menu option (sorting) -- maybe have a list to choose from?
        if(id == R.id.sort){

            //old stuff from project3
            //NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
            //        findFragmentById(R.id.fragment); //this one refers to activity_main.xml

            //assert navHostFragment != null;
            //NavController controller = navHostFragment.getNavController();
            //controller.navigate(R.id.action_global_fragment_info);

            Toast.makeText(this, "Not Implemented Yet",
                    Toast.LENGTH_LONG).show();
        }


        if (id == R.id.unin){
            Intent delete = new Intent(Intent.ACTION_DELETE,
                    Uri.parse("package:" + getPackageName()));
            startActivity(delete);
        }
        return super.onOptionsItemSelected(item);
    }
}