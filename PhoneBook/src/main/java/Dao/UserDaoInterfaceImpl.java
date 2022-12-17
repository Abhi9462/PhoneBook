package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.User;
import Util.ConnectionProvider;

public class UserDaoInterfaceImpl implements UserDaoInterface {
	Connection con = null;
	String query = null;
	PreparedStatement pstmt = null;
    User user=null;
	@Override
	public User loginUser(String userEmail, String password) {
		System.out.println(userEmail+" "+password);
		con = ConnectionProvider.createConnection();
		try {
			query = "select * from users where userEmail=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);
			ResultSet rs = pstmt.executeQuery();
			//System.out.println(rs.getString(2));
			rs.next();
			System.out.println(rs.getString(3));
			if (password.equals(rs.getString(3)))
			{
				user=new User();
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword("*************");
				user.setUserEmail(rs.getString(4));
			    user.setUserPhone(rs.getString(5));
			}
			
			return user;		
				

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean signUp(User user) {
		con = ConnectionProvider.createConnection();
		try {
			query = "insert into users(userName,userPassword,userEmail,userPhone)values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserPhone());
			System.out.println(user+" userDAO");

			if (pstmt.executeUpdate()!=0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteUser(int userID) {
		con = ConnectionProvider.createConnection();
		try {
			query = "delete from users where userID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userID);
			if (pstmt.executeUpdate() != 0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User searchUser(int id) {
		con = ConnectionProvider.createConnection();
		try {
			query = "select * from users where userID=?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				user=new User();
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword("*************");
				user.setUserEmail(rs.getString(4));
			    user.setUserPhone(rs.getString(5));
			}
			
			return user;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<User> listUser() {
		ArrayList<User> al=new ArrayList<>();
		con = ConnectionProvider.createConnection();
		try {
			query = "select * from users";
			pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				user=new User();
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword("**************");
				user.setUserEmail(rs.getString(4));
			    user.setUserPhone(rs.getString(5));
			    al.add(user);
			}
			
			return al;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		con = ConnectionProvider.createConnection();
		try {
			query = "update user set userName=?,userPassword=?,userEmail=?,userPhone=? where userID=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserPhone());
			pstmt.setInt(5, user.getUserID());
			
			if(pstmt.executeUpdate()!=0)
				return true;
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
