package com.example.kfc.prac10;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kfc.prac10.datamanagers.TaskDataManager;
import com.example.kfc.prac10.datamodels.Task;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static TaskDataManager taskDataManager = new TaskDataManager();

    // UI widgets
    //
    FloatingActionButton buttonAdd;
    GridView gridViewTasks;

    static int TASK_REQUEST_CODE = 1;

    static int FIREBASE_SIGN_IN_REQUEST_CODE = 2;
    FloatingActionButton buttonSignOut;

    public static FirebaseUser firebaseUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewTasks = findViewById(R.id.gridViewTasks);
        gridViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // When the taps on one of the items in the GridVIew, show a popup dialog
                // that allows user to select one of the following choices:
                //  Edit Task / Complete Task
                //
                new AlertDialog.Builder(MainActivity.this)
                    .setTitle("What do you want to do?")
                    .setItems(new String[]{"Edit Task", "Complete Task"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int itemSelected) {

                            final Task task = (Task) gridViewTasks.getItemAtPosition(position);

                            // TODO:
                            // Integrate with Firebase
                        }
                    })
                    .show();

            }
        });

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO:
                // Pass an empty String "id" into the EditActivity.

            }
        });

        // TODO:
        // Firebase Authentication.
    }

    // TODO:
    // Add methods for Firebase Authentication.
    public void loginWithFirebaseAuth(){

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),

//                    new AuthUI.IdpConfig.PhoneBuilder().build(),
//                    new AuthUI.IdpConfig.GoogleBuilder().build(),
//                    new AuthUI.IdpConfig.FacebookBuilder().build(),
//                    new AuthUI.IdpConfig.TwitterBuilder().build());


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    FIREBASE_SIGN_IN_REQUEST_CODE);
        }
    }

    public void refreshList()
    {
        // TODO:
        // Integrate with Firebase to load the full list of tasks
        // and display it in our GridView.

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TASK_REQUEST_CODE) {
            refreshList();
        }



        // TODO:
        // Handle what happens after the Firebase Authentication completes.
        // (The authentication may be successful or may have failed)
    }
}
