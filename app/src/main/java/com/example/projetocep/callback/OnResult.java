package com.example.projetocep.callback;

import com.google.android.gms.tasks.OnCompleteListener;

import java.util.List;

public interface OnResult {
    void execute(List<String> lista);
}
