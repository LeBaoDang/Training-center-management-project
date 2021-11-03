/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.entity;

/**
 *
 * @author LeBaoDang
 */
public class NhanVien {

    private String maNV;
    private String matKhau;
    private String hoTen;
    private boolean vaiTro;
    private String Email;
    private String MKEmail;
    private String gioiTinh;

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMKEmail() {
        return MKEmail;
    }

    public void setMKEmail(String MKEmail) {
        this.MKEmail = MKEmail;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public NhanVien(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return this.hoTen;
    }

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoTen, boolean vaiTro, String Email, String MKEmail) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
        this.Email = Email;
        this.MKEmail = MKEmail;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

}
