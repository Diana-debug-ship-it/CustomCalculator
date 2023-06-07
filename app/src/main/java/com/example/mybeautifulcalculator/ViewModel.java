package com.example.mybeautifulcalculator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {
    public ViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<Double> result = new MutableLiveData<>();

    public LiveData<Double> getResult() {
        return result;
    }

    private String retrieveProblem(String problem) {
        return CalculateExpression.expressionToRPN(problem);
    }

    public void solveTheProblem(String problem) {
        String rpn = retrieveProblem(problem);
        double res = CalculateExpression.RPNtoResult(rpn);
        result.setValue(res);
    }
}
