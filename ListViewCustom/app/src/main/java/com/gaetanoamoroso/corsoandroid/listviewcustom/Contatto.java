package com.gaetanoamoroso.corsoandroid.listviewcustom;

import android.graphics.drawable.Drawable;

public class Contatto {
    private  String m_name;
    private String m_phone_number;
    private Drawable m_drawable;
    public Contatto(String name, String phone_number, Drawable drawable) {
        m_name = name;
        m_phone_number = phone_number;
        m_drawable = drawable;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public String getPhone_number() {
        return m_phone_number;
    }

    public void setPhone_number(String phone_number) {
        m_phone_number = phone_number;
    }

    public Drawable getDrawable() {
        return m_drawable;
    }

    public void setDrawable(Drawable drawable) {
        m_drawable = drawable;
    }
}
