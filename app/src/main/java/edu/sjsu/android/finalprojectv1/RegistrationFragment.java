package edu.sjsu.android.finalprojectv1;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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

public class RegistrationFragment extends Fragment {
    private final String AUTHORITY = "edu.sjsu.android.dataprovider";
    private final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private EditText fullnameEditText;
    private EditText emailEditText;
    private EditText mobileEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private ImageView passwordIcon;
    private ImageView confirmPasswordIcon;
    private AppCompatButton signUpButton;
    private TextView signInButton;
    private boolean passwordShowing = false;
    private boolean confirmPasswordShowing = false;
    NavController controller;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://cryptocurrencytracker-b2ea4-default-rtdb.firebaseio.com/");

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        controller = NavHostFragment.findNavController(this);

        fullnameEditText = view.findViewById(R.id.fullnameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        mobileEditText = view.findViewById(R.id.mobileEditText);

        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        passwordIcon = view.findViewById(R.id.passwordIcon);
        confirmPasswordIcon = view.findViewById(R.id.confirmPasswordIcon);
        passwordIcon.setOnClickListener(this::passwordIconOnClick);
        confirmPasswordIcon.setOnClickListener(this::confirmPasswordIconOnClick);

        view.findViewById(R.id.signInButton).setOnClickListener(v ->
                controller.navigate(R.id.action_registrationFragment_to_loginFragment));
        signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this::signUpButtonOnClick);

        return view;
    }

    public void passwordIconOnClick(View view) {
        if (passwordShowing) {
            passwordShowing = false;
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordIcon.setImageResource(R.drawable.passwordhide);
        } else {
            passwordShowing = true;
            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordIcon.setImageResource(R.drawable.passwordshow);
        }

        passwordEditText.setSelection(passwordEditText.length());
    }

    public void confirmPasswordIconOnClick(View view) {
        if (confirmPasswordShowing) {
            confirmPasswordShowing = false;
            confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            confirmPasswordIcon.setImageResource(R.drawable.passwordhide);
        } else {
            confirmPasswordShowing = true;
            confirmPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            confirmPasswordIcon.setImageResource(R.drawable.passwordshow);
        }

        confirmPasswordEditText.setSelection(confirmPasswordEditText.length());
    }

    public void signUpButtonOnClick(View view) {
        String fullname = fullnameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String mobile = mobileEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = passwordEditText.getText().toString();

        if (fullname.isEmpty() || email.isEmpty() || mobile.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (validatePassword()) {
            //Later task: change to materialUI TextInputLayout with app:helperText
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(mobile))
                        Toast.makeText(getContext(), "Email is already registered", Toast.LENGTH_SHORT).show();
                    else{
                        databaseReference.child("users").child(mobile).child("fullname").setValue(fullname);
                        databaseReference.child("users").child(mobile).child("email").setValue(mobile);
                        databaseReference.child("users").child(mobile).child("password").setValue(password);
                        Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        controller.navigate(R.id.action_registrationFragment_to_loginFragment);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void addUser(View view) {
        ContentValues values = new ContentValues();
        values.put("fullname", fullnameEditText.getText().toString());
        values.put("email", emailEditText.getText().toString());
        values.put("mobile", mobileEditText.getText().toString());
        values.put("password", passwordEditText.getText().toString());

        if (getActivity().getContentResolver().insert(CONTENT_URI, values) != null)
            Toast.makeText(getContext(), "User Added", Toast.LENGTH_SHORT).show();
    }

    public boolean validatePassword() {
        if (passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString()))
            return false;
        else
            return true;
    }
}