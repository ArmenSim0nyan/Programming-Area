package com.example.programmingarea;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.programmingarea.dataclass.CodeExecutionBody;
import com.example.programmingarea.dataclass.CodeExecutionResponse;
import com.example.programmingarea.http.CodeExecution;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CodeFightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_codefight);

        Intent currentIntent = getIntent();

        String programmingLanguage = currentIntent.getStringExtra("language");

        Button runButton = findViewById(R.id.loginSubmitButton);
        EditText codeEditor = findViewById(R.id.emailRegisterInput);

        codeEditor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Move to the next line
                    codeEditor.append("\n");
                    return true;
                }
                return false;
            }
        });

        runButton.setOnClickListener(x -> {
            String code = codeEditor.getText().toString();

            CodeExecutionBody body = new CodeExecutionBody(code, getProgrammingLanguage(programmingLanguage), "0", "b9a790e4cb35cd05c0239c7cb0b5367a", "ec65f064e7cbd4e1f349a3521159cbded4721329e5ce09c27803045d3039cfee");
            sendCodeExecutionRequest(body, this);
        });
    }

    private String getProgrammingLanguage(String language) {
        switch (language) {
            case "Java Script":
                return "nodejs";
            case "Python":
                return "python3";
            case "Java":
                return "java";
            case "Kotlin":
                return "kotlin";
            default:
                return "";
        }
    }


    private void sendCodeExecutionRequest(CodeExecutionBody body, CodeFightActivity context) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.jdoodle.com/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        CodeExecution client = retrofit.create(CodeExecution.class);
        Call<CodeExecutionResponse> call = client.executeCode(body);

        call.enqueue(new Callback<CodeExecutionResponse>() {
            @Override
            public void onResponse(Call<CodeExecutionResponse> call, Response<CodeExecutionResponse> response) {
                assert response.body() != null;
                String message = "";

                CodeExecutionResponse body = response.body();

                message += "Output: " + body.getOutput();
                message += "Execution time: " + body.getCpuTime() + '\n';
                message += "Used memory: " + body.getMemory();

                new AlertDialog.Builder(context)
                        .setTitle("Code output")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            @Override
            public void onFailure(Call<CodeExecutionResponse> call, Throwable t) {
                Toast.makeText(CodeFightActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}
