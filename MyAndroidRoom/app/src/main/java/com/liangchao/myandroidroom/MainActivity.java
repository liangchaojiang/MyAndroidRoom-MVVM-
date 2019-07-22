package com.liangchao.myandroidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Application application;

    Button button1,button2;
    TextView tv1,tv2,tv3;
    EditText editText1;
    WordViewModel wordViewModel;
    LiveData<List<Word>> liveData;

    List<Word> wordList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.bt1);
        button2 = (Button)findViewById(R.id.bt2);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        editText1 = (EditText)findViewById(R.id.et1);

        application = getApplication();
        Word initWord = new Word("Liangchao");
       // wordViewModel = new WordViewModel(application);



        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.insert(initWord);

        wordViewModel.getmAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                Log.i("Liangchao","changed data: "+words.size());
                for(int i=0;i<words.size();i++){
                    Log.i("Liangchao","data is : "+words.get(i).getWord());
                }
                tv1.setText(words.get(words.size()-2).getWord());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              liveData =  wordViewModel.getmAllWords();

              wordList = liveData.getValue();

            //  Log.i("Liangchao","the size of worlist is : "+wordList.size());


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word addWord = new Word(editText1.getText().toString());
                wordViewModel.insert(addWord);
            }
        });

    }



}
