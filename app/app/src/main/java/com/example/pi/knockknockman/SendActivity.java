package com.example.pi.knockknockman;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SendActivity extends AppCompatActivity {

    Button btnMemo, btnCongrat, btnThank, btnWarn;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference DataRef = firebaseDatabase.getReference("homeCCTV");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        btnMemo = findViewById(R.id.btnMemo);
        btnCongrat = findViewById(R.id.btnCongrat);
        btnThank = findViewById(R.id.btnThank);
        btnWarn = findViewById(R.id.btnWarn);

        btnMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMemo();
            }
        });

        btnCongrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCongrat();
            }
        });

        btnThank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showThank();
            }
        });

        btnWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWarn();
            }
        });
    }

    void showMemo() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("잠시만 기달려주세요.");
        ListItems.add("경비실에 맡겨주세요.");
        ListItems.add("문 앞에 놓아주세요.");
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전달할 메세지를 클릭하여 주세요");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                String selectedText = "\"1-" + (pos + 1) + "\"";
                DataRef.child("test").setValue(selectedText);
            }
        });
        builder.show();
    }

    void showCongrat() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("아빠 생신 축하드려요!");
        ListItems.add("엄마 생신 축하드려요!");
        ListItems.add("형아 생일 축하해!");
        ListItems.add("누나 생일 축하해!");
        ListItems.add("오빠 생일 축하해!");
        ListItems.add("언니 생일 측히해!");
        ListItems.add("할머니 생신 축하드려요!");
        ListItems.add("할아버지 생신 축하드려요!");
        ListItems.add("동생아 생일 축하해!");
        ListItems.add("메리 크리스마스!");
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전달할 메세지를 클릭하여 주세요");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                String selectedText = "\"2-" + (pos + 1) + "\"";
                DataRef.child("test").setValue(selectedText);
            }
        });
        builder.show();
    }

    void showThank() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("오늘도 감사합니다!");
        ListItems.add("오늘 많이 덥죠? 화이팅!");
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전달할 메세지를 클릭하여 주세요");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                String selectedText = "\"3-" + (pos + 1) + "\"";
                DataRef.child("test").setValue(selectedText);
            }
        });
        builder.show();
    }

    void showWarn() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("장난치지 마라!");
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전달할 메세지를 클릭하여 주세요");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                String selectedText = "\"4-" + (pos + 1) + "\"";
                DataRef.child("test").setValue(selectedText);
            }
        });
        builder.show();
    }
}
