package com.androidapps.cm.geeksdictionary.data;

import java.util.List;

// +----------------------------------------------------------------------
// | CreateTime: 16/5/9 
// +----------------------------------------------------------------------
// | Author:     cm
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public interface INewWordsModel {
   List<NewWord> getNewWords();

    void insertWords(String English,String Chinese);
    void deleteWords(String English);

}
