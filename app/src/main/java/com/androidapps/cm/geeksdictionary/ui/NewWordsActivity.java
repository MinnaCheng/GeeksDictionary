package com.androidapps.cm.geeksdictionary.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.androidapps.cm.geeksdictionary.R;
import com.androidapps.cm.geeksdictionary.adapter.NewWordsAdapter;
import com.androidapps.cm.geeksdictionary.data.NewWord;
import com.androidapps.cm.geeksdictionary.presenter.NewWordsPresenter;

import java.util.ArrayList;
import java.util.List;


public class NewWordsActivity extends ActionBarActivity implements View.OnClickListener {
    NewWordsPresenter newWordsPresenter;
    private List<NewWord> list ;
    private List<NewWord> listAfterSort;
    private ImageButton IbBack;
    private ImageButton IbReflash;
    private ImageButton IbDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_words);
        initView();
        getNewWordsList();
        //listAfterSort = sortNewWords(list);
        setListView(list);

    }

    private void initView() {
        IbBack = (ImageButton)findViewById(R.id.ivTitleBtnBack);
        IbReflash = (ImageButton)findViewById(R.id.iVReflash);
        IbDelete = (ImageButton)findViewById(R.id.IbDelete);
       // IbDelete.setOnClickListener(this);
        IbReflash.setOnClickListener(this);
        IbBack.setOnClickListener(this);
    }

    private void getNewWordsList() {
        newWordsPresenter = new NewWordsPresenter(this);
        list = new ArrayList<>();
        list = newWordsPresenter.getNewWords();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setListView(final List<NewWord> listAfterSort) {
        NewWordsAdapter newWordsAdapter = new NewWordsAdapter(
                NewWordsActivity.this, R.layout.new_words_item, listAfterSort);
        ListView listView = (ListView)findViewById(R.id.listViewWords);
        listView.setAdapter(newWordsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewWord newWord = list.get(position);
                Intent intent = new Intent(NewWordsActivity.this,WordsDetailActivity.class);
                intent.putExtra("word",newWord.getWord());
                intent.putExtra("mean",newWord.getMeaing());
                startActivity(intent);
            }
        });
        //长按列表项弹出删除生词对话框
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                IbDelete.setVisibility(View.VISIBLE);
                dialog();
                return false;
            }
        });
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewWordsActivity.this);
        builder.setMessage("确认删除该生词吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                NewWordsActivity.this.finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ivTitleBtnBack:
                Intent intent = new Intent(NewWordsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.iVReflash:
                getNewWordsList();
                //listAfterSort = sortNewWords(list);
                setListView(list);
                break;
            case R.id.IbDelete:
                break;
        }
    }

    /*private List<NewWord> sortNewWords(List<NewWord> list) {

        return;
    }*/
}
