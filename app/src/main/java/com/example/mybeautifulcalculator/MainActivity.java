package com.example.mybeautifulcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private TextView textViewProblem;

    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;

    List<TextView> tvNumbers = new ArrayList<>();

    private TextView tvEraseAll;
    private TextView tvErase;
    private TextView tvNot;
    private TextView tvDivision;
    private TextView tvMultiplication;
    private TextView tvMinus;
    private TextView tvPlus;
    private TextView tvPoint;
    private TextView tvEquals;

    private TextView tvStart;
    private TextView tvEnd;
    private TextView tvSettings;
    private static StringBuilder stringBuilder = new StringBuilder();
    private static boolean isChar = true;


    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        initViews();
        setOnClickListeners();
    }

    private String getString(TextView textView) {
        return textView.getText().toString();
    }

    private void updateProblem(String s) {
        stringBuilder.append(s);
        textViewProblem.setText(stringBuilder.toString());
    }

    private void setOnClickListeners() {
        for (int i = 0; i < tvNumbers.size(); i++) {
            final int f = i;
            tvNumbers.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateProblem(String.valueOf(f));
                    isChar = false;
                }
            });
        }

        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChar) {
                    updateProblem(getString(tvPlus));
                    isChar = true;
                }
            }
        });

        tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChar) {
                    updateProblem(getString(tvMinus));
                    isChar = true;
                }
            }
        });

        tvDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChar) {
                    updateProblem(getString(tvDivision));
                    isChar = true;
                }
            }
        });

        tvMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChar) {
                    updateProblem(getString(tvMultiplication));
                    isChar = true;
                }
            }
        });

        tvPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChar) {
                    updateProblem(getString(tvPoint));
                    isChar = true;
                }
            }
        });

        tvEraseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBuilder.setLength(0);
                textViewProblem.setText("");
                textViewResult.setText("");
                isChar = true;
            }
        });

        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProblem(getString(tvStart));
                isChar = false;
            }
        });

        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProblem(getString(tvEnd));
            }
        });

        tvEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringBuilder.length() != 0) {
                    viewModel.solveTheProblem(stringBuilder.toString());
                    viewModel.getResult().observe(MainActivity.this, new Observer<Double>() {
                        @Override
                        public void onChanged(Double result) {
                            textViewResult.setText(String.valueOf(result));
                        }
                    });
                }
            }
        });

        char charToErase;
        tvErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringBuilder.length() > 0) {
                    textViewProblem.setText(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
                    textViewResult.setText("");
                    isChar = false;
                }
            }
        });

        tvNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = stringBuilder.length() - 1; i >= 0; i--) {
                    if (stringBuilder.charAt(i) == '+' || stringBuilder.charAt(i) == '-') {
                        stringBuilder.insert(i + 1, "(-");
                        break;
                    }
                    else if (i == 0) {
                        stringBuilder.insert(0, "(-");
                    }
                }
                textViewProblem.setText(stringBuilder.toString());
            }
        });

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Данная функция пока недоступна", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        textViewResult = findViewById(R.id.textViewResult);
        textViewProblem = findViewById(R.id.textViewProblem);

        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);

        tvNumbers.add(tv0);
        tvNumbers.add(tv1);
        tvNumbers.add(tv2);
        tvNumbers.add(tv3);
        tvNumbers.add(tv4);
        tvNumbers.add(tv5);
        tvNumbers.add(tv6);
        tvNumbers.add(tv7);
        tvNumbers.add(tv8);
        tvNumbers.add(tv9);

        tvEraseAll = findViewById(R.id.tvEraseAll);
        tvErase = findViewById(R.id.tvErase);
        tvDivision = findViewById(R.id.tvDivision);
        tvMultiplication = findViewById(R.id.tvMultiplication);
        tvPlus = findViewById(R.id.tvPlus);
        tvMinus = findViewById(R.id.tvMinus);
        tvNot = findViewById(R.id.tvNot);
        tvPoint = findViewById(R.id.tvPoint);
        tvEquals = findViewById(R.id.tvEquals);
        tvSettings = findViewById(R.id.tvChangeInstruments);
        tvStart = findViewById(R.id.tvStart);
        tvEnd = findViewById(R.id.tvEnd);
    }
}