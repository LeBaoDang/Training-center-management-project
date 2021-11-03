package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.HocVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO extends EduSysDAO<HocVien, Integer>{
    
    String INSERT_SQL ="INSERT INTO HocVien(MaKH, MaNH, Diem, Email) VALUES(?, ?, ?, ?)";
    String UPDATE_SQL ="UPDATE HocVien SET MaKH=?, MaNH=?, Diem=?, Email=? WHERE MaHV=?";
    String DELETE_SQL ="DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL ="SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL ="SELECT * FROM HocVien WHERE MaHV=?";
    
    public void insert(HocVien model){
        XJdbc.update(INSERT_SQL, 
                model.getMaKH(), 
                model.getMaNH(), 
                model.getDiem(),
                model.getEmail());  
    }
    
    public void update(HocVien model){
        XJdbc.update(UPDATE_SQL, 
                model.getMaKH(), 
                model.getMaNH(), 
                model.getDiem(),
                model.getEmail(),
                model.getMaHV());
    }
    
    public void delete(Integer MaHV){
        XJdbc.update(DELETE_SQL, MaHV);
    }
    
    public List<HocVien> selectAll(){
        return selectBySql(SELECT_ALL_SQL);
    }
    
    public HocVien selectById(Integer mahv){
        List<HocVien> list = selectBySql(SELECT_BY_ID_SQL, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<HocVien> selectBySql(String sql, Object...args){
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    HocVien entity=new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
                    entity.setEmail(rs.getString("Email"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    // lấy học viên của khóa học
    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql="SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }
}
