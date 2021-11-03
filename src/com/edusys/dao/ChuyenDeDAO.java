package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.ChuyenDe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    String INSERT_SQL ="INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL ="UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
    String DELETE_SQL ="DELETE FROM ChuyenDe WHERE MaCD=?";
    String SELECT_ALL_SQL ="SELECT * FROM ChuyenDe";
    String SELECT_BY_ID_SQL ="SELECT * FROM ChuyenDe WHERE MaCD=?";
    
    public void insert(ChuyenDe model){
        XJdbc.update(INSERT_SQL, 
                model.getMaCD(), 
                model.getTenCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getHinh(),
                model.getMoTa());
    }
    
    public void update(ChuyenDe model){
        XJdbc.update(UPDATE_SQL, 
                model.getTenCD(), 
                model.getHocPhi(), 
                model.getThoiLuong(), 
                model.getHinh(), 
                model.getMoTa(),
                model.getMaCD());
    }
    
    public void delete(String MaCD){
        XJdbc.update(DELETE_SQL, MaCD);
    }
    
    public List<ChuyenDe> selectAll(){
        return selectBySql(SELECT_ALL_SQL);
    }
    
    public ChuyenDe selectById(String macd){
        List<ChuyenDe> list = selectBySql(SELECT_BY_ID_SQL, macd);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<ChuyenDe> selectBySql(String sql, Object...args){
        List<ChuyenDe> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    ChuyenDe entity=new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setMoTa(rs.getString("MoTa"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
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
}
