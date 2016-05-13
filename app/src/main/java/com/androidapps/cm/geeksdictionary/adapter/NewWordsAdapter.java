package com.androidapps.cm.geeksdictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidapps.cm.geeksdictionary.R;
import com.androidapps.cm.geeksdictionary.data.NewWord;

import java.util.List;

// +----------------------------------------------------------------------
// | CreateTime: 16/5/12 
// +----------------------------------------------------------------------
// | Author:     cm
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public class NewWordsAdapter extends ArrayAdapter<NewWord> {
    private int resourceId;

    public NewWordsAdapter(Context context,  int textViewResourceId, List<NewWord> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewWord newWord = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView newWords = (TextView)view.findViewById(R.id.new_word);
        TextView meaning = (TextView)view.findViewById(R.id.meaning);
        newWords.setText(newWord.getWord());
        meaning.setText(newWord.getMeaing());
        return view;
    }
}
