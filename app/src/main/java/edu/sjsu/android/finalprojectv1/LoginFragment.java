package edu.sjsu.android.finalprojectv1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class LoginFragment extends Fragment {
    private final String AUTHORITY = "edu.sjsu.android.dataprovider";
    private final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private boolean passwordShowing = false;
    EditText emailEditText;
    EditText passwordEditText;
    ImageView passwordIcon;
    TextView signUpButton;
    User user;
    NavController controller;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://cryptocurrencytracker-b2ea4-default-rtdb.firebaseio.com/");

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        controller = NavHostFragment.findNavController(this);

        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);

        passwordIcon = view.findViewById(R.id.passwordIcon);
        passwordIcon.setOnClickListener(this::passwordIconOnClick);

        view.findViewById(R.id.signUpButton).setOnClickListener(v ->
                controller.navigate(R.id.action_loginFragment_to_registrationFragment));
        view.findViewById(R.id.signInButton).setOnClickListener(this::signInButtonOnClick);

        return view;
    }

    public void passwordIconOnClick(View view) {
        if (passwordShowing) {
            passwordShowing = false;
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordIcon.setImageResource(R.drawable.passwordhide);
        }
        else {
            passwordShowing = true;
            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordIcon.setImageResource(R.drawable.passwordshow);
        }
        passwordEditText.setSelection(passwordEditText.length());
    }

    public void signInButtonOnClick(View view) {
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if(email.isEmpty() || password.isEmpty())
            Toast.makeText(getContext(), "Please enter your email and password", Toast.LENGTH_SHORT).show();
        else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(email)) {
                        final String getPassword = snapshot.child(email)
                                .child("password").getValue(String.class);
                        if(password.equals(getPassword)) {
                            Toast.makeText(getContext(), "Successfully Signed-In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                        }
                        else
                            Toast.makeText(getContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getContext(), "Email Not Found", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}