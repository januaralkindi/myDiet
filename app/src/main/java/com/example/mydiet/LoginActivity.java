package com.example.mydiet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.labelEmail)
    TextView labelEmail;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.labelPassword)
    TextView labelPassword;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
