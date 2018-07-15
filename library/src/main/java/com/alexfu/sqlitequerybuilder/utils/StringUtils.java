package com.alexfu.sqlitequerybuilder.utils;

import android.text.TextUtils;

public class StringUtils {
  public static String join(CharSequence delimiter, Object... array) {
    return TextUtils.join(delimiter, array);
  }
}
