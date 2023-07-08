package com.doan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class City {
    String maTinh, tenTinh;
    String hinhAnh;

    public City() {
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public City(String maTinh, String tenTinh, String hinhAnh) {
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
        this.hinhAnh = hinhAnh;
    }

    public City(String tenTinh, String hinhAnh) {
        this.tenTinh = tenTinh;
        this.hinhAnh = hinhAnh;
    }

    public static Bitmap convertStringToBitmapFromAccess (Context context, String filename) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
