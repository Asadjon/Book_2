package com.cyberpanterra.book_2.interfaces;

/* 
    The creator of the OnClickListener interface is Asadjon Xusanjonov
    Created on 18:29, 23.03.2022
*/

import androidx.annotation.NonNull;

public interface OnClickListener<T>{
    void onClick(@NonNull T value);
}

