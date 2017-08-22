package com.cboss.ksi.admin.services.impl;

import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.ValueEncoder;

/**
 * ValueEncoder for QuestionOption problemTask.
 *
 * Created by ishafigullin on 12.07.2017.
 */
public class QuestionOptionEncoder implements ValueEncoder<QuestionOption> {
    private DictionaryDao dao;

    public QuestionOptionEncoder(DictionaryDao dictionaryDao) {
        dao = dictionaryDao;
    }

    @Override
    public String toClient(QuestionOption value) {
        return String.valueOf(value.<Long>getId());
    }

    @Override
    public QuestionOption toValue(String clientValue) {
        Long optionId = Long.valueOf(clientValue);
        return dao.getQuestionOption(optionId);
    }
}
