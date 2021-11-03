package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    
    String INSERT_SQL ="INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro,Email,MKEmail,GioiTinh) VALUES (?,?, ?, ?,?,?, ?)";
    String UPDATE_SQL ="UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=?,Email=?,MKEmail=?,GioiTinh=? WHERE MaNV=?";
    String DELETE_SQL ="DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL ="SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL ="SELECT * FROM NhanVien WHERE MaNV=?";
    
    public void insert(NhanVien model){
        XJdbc.update(INSERT_SQL, 
                model.getMaNV(), 
                model.getMatKhau(), 
                model.getHoTen(), 
                model.isVaiTro(),
                model.getEmail(),
                model.getMKEmail(),
                model.getGioiTinh()
        );
        
    }
    
    public void update(NhanVien model){
        XJdbc.update(UPDATE_SQL, 
                model.getMatKhau(), 
                model.getHoTen(), 
                model.isVaiTro(),
                model.getEmail(),
                model.getMKEmail(),
                model.getGioiTinh(),
                model.getMaNV()
        );
    }
    
    public void delete(String MaNV){
        XJdbc.update(DELETE_SQL, MaNV);
    }
    
    public List<NhanVien> selectAll(){
        return this.selectBySql(SELECT_ALL_SQL);
    }
 
    public NhanVien selectById(String manv){
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NhanVien> selectBySql(String sql, Object...args){
        List<NhanVien> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    NhanVien entity=new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setMKEmail(rs.getString("MKEmail"));
                    entity.setGioiTinh(rs.getString("GioiTinh"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<NhanVien> selectByNhanVien(String CauHoiBaoMat){
        String sql="SELECT * FROM NhanVien WHERE CauHoiBaoMat=?";
        return this.selectBySql(sql, CauHoiBaoMat);
    }
}
