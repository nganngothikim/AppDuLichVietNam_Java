package com.doan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import kotlin.text.UStringsKt;

public class Location {
    String maDD, tenDD, moTa, maTinh, diaChi, tenTinh;
    String hinhAnh;

    public String getMaDD() {
        return maDD;
    }

    public void setMaDD(String maDD) {
        this.maDD = maDD;
    }

    public String getTenDD() {
        return tenDD;
    }

    public void setTenDD(String tenDD) {
        this.tenDD = tenDD;
    }
    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Location(String maDD, String tenDD, String hinhAnh) {
        this.maDD = maDD;
        this.tenDD = tenDD;
        this.hinhAnh = hinhAnh;
    }

    public Location(String tenDD, String hinhAnh) {
        this.tenDD = tenDD;
        this.hinhAnh = hinhAnh;
    }

    public Location(String maDD, String tenDD, String moTa, String maTinh, String diaChi, String hinhAnh, String tenTinh) {
        this.maDD = maDD;
        this.tenDD = tenDD;
        this.moTa = moTa;
        this.maTinh = maTinh;
        this.diaChi = diaChi;
        this.hinhAnh = hinhAnh;
        this.tenTinh=tenTinh;
    }

    public Location() {
    }

    public static Bitmap convertStringToBitmapFromAccess(Context context, String fileName)
    {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
