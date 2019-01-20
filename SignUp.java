package com.example.user.signuppage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                                                                    //"(?=.*[0-9])" +               //at least 1 digit
                                                                    "(?=.*[a-z])" +               //at least 1 lower case letter
                                                                    "(?=.*[A-Z])" +               //at least 1 upper case letter
                                                                    //"(?=.*[a-zA-Z])" +            //any letter
                                                                    "(?=.*[@#$%^&+=])" +          //at least 1 special character
                                                                    //"(?=\\S+$)" +                 //no white space
                                                                    //".{4,}" +                     //at least 4 characters
                                                                    "$");
    public EditText textInputEmail;
    public EditText textInputUsername;
    public EditText textInputPassword;
    public EditText textInputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
        textInputConfirmPassword = findViewById(R.id.text_input_confirm_password);
    }

    //Self explanatory
    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();

        if(emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        }

        //Given below is a predefined pattern that validates email address; emailInput string should be passed into matcher; returns true if they match
        //Keep the cursor on EMAIL_ADDRESS below and click on "ctrl + b" to get its declaration. There you can see that it is a constant

        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Invalid Email Address. Please enter a valid email address.");
            return false;
        }
        else {
            textInputEmail.setError(null);
            return true;
        }
    }


    private boolean validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();

        if(usernameInput.isEmpty()) {
            textInputUsername.setError("Field cannot be empty");
            return false;
        }
        else if(usernameInput.length() > 15) {
            textInputUsername.setError("Username too long");
            return false;
        }
        else {
            textInputUsername.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();

        if(passwordInput.isEmpty()) {
            textInputPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            return false;
        }
        else {
            textInputPassword.setError(null);
            return true;
        }
     }


     private boolean confirmPassword() {
        String confirmPasswordInput = textInputConfirmPassword.getText().toString().trim();
        String passwordInput = textInputPassword.getText().toString().trim();

        if(confirmPasswordInput.isEmpty()) {
            textInputConfirmPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!confirmPasswordInput.equals(passwordInput)) {
            textInputConfirmPassword.setError("Does not match above input password");
            return false;
        }
        else {
            textInputConfirmPassword.setError(null);
            return true;
        }
     }

     public void confirmInput(View v) {
        if(!validateEmail() | !validatePassword() |!validateUsername() | !confirmPassword()) {
            return;
        }

        String input = "Email: " + textInputEmail.getText().toString();
        input += "\n";
        input += "Username: " + textInputUsername.getText().toString();
        input += "\n";
        input += "Password: " + textInputPassword.getText().toString();

         Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
     }
}
