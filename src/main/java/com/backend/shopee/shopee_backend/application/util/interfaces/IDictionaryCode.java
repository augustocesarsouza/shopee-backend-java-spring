package com.backend.shopee.shopee_backend.application.util.interfaces;

public interface IDictionaryCode {
    Integer getKeyDictionary(String key);

    Integer removeKeyDictionary(String key);

    Integer putKeyValueDictionary(String key, Integer value);
}
