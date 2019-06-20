package com.dao;

import java.sql.*;

public class BaseDao {
    //获取连接对象
    public Connection getConn(){
        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/student";
            Connection conn = DriverManager.getConnection(url,"root","root");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //释放资源
    public void close(ResultSet set , PreparedStatement ps,Connection conn){

        if(set!=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //增删改
    public int update(String sql,Object[] obj){
        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if(obj!=null){
                //占位符赋值
                for(int i = 0;i<obj.length;i++){
                    ps.setObject(i+1,obj[i]);
                }
            }
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,conn);
        }
        return 0;
    }
    //查询
	public void print(){
		System.out.print("hello,world");
	}
}
