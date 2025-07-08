// TrollSwift Executor Base (Android Native UI)
// Dibuat oleh Kenzo untuk eksperimen executor asli

package com.kenzo.trollswift;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity {
    EditText scriptInput;
    Button executeBtn, clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout utama
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        // Judul
        TextView title = new TextView(this);
        title.setText("TrollSwift Executor");
        title.setTextSize(24);
        layout.addView(title);

        // Input script
        scriptInput = new EditText(this);
        scriptInput.setHint("Paste script here...");
        scriptInput.setMinHeight(300);
        layout.addView(scriptInput);

        // Tombol EXECUTE
        executeBtn = new Button(this);
        executeBtn.setText("EXECUTE");
        layout.addView(executeBtn);

        // Tombol CLEAR
        clearBtn = new Button(this);
        clearBtn.setText("CLEAR");
        layout.addView(clearBtn);

        setContentView(layout);

        // Tombol CLEAR: hapus teks
        clearBtn.setOnClickListener(v -> scriptInput.setText(""));

        // Tombol EXECUTE: simpan script
        executeBtn.setOnClickListener(v -> {
            String script = scriptInput.getText().toString();
            if (!script.isEmpty()) {
                saveScriptToFile(script);
                Toast.makeText(this, "Script saved for injection", Toast.LENGTH_SHORT).show();
                // Nanti: inject via Frida / Native
            } else {
                Toast.makeText(this, "Script is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Simpan ke /storage/emulated/0/trollswift/script.txt
    private void saveScriptToFile(String script) {
        try {
            File folder = new File(Environment.getExternalStorageDirectory(), "trollswift");
            if (!folder.exists()) folder.mkdirs();

            File file = new File(folder, "script.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(script.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
          }
