package ControllerService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ContactDaoInterfaceImpl;
import Dao.UserDaoInterfaceImpl;
import Model.Contact;
import Model.User;

public class ControllerServiceInterfaceImpl implements ControllerServiceInterface {
	ContactDaoInterfaceImpl cdii = new ContactDaoInterfaceImpl();
	UserDaoInterfaceImpl udii = new UserDaoInterfaceImpl();
	RequestDispatcher rd = null;

	@Override
	public boolean insertContact(HttpServletRequest request, HttpServletResponse response) {
		String contactID = request.getParameter("contactID");
        String userID=request.getParameter("userID");
		String contactName = request.getParameter("Name");
		String contactEmail = request.getParameter("Email");
		String contactAddress = request.getParameter("Address");
		String contactPhone = request.getParameter("Phone");
		System.out.println(userID +"userID");
		Contact contact = new Contact(userID,contactName, contactEmail, contactAddress, contactPhone);

		if (cdii.createContact(contact))
			return true;
		return false;

	}

	@Override
	public boolean deleteContact(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("contactID"));
		System.out.println(id + " deleteContact");

		if (cdii.deleteContact(id))
			return true;

		return false;
	}

	@Override
	public void editContact(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("contactID"));
		// System.out.println(id);
		Contact contact = cdii.searchContact(id);
		request.setAttribute("contact", contact);
		rd = request.getRequestDispatcher("update.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean updateContact(HttpServletRequest request, HttpServletResponse response) {
		Contact contact = new Contact();
		String id = request.getParameter("contactID");
		if (id == null || id.isEmpty()) {
			insertContact(request, response);
		}

		else {
			contact.setContactID(Integer.parseInt(id));
			contact.setContactName(request.getParameter("Name"));
			contact.setContactEmail(request.getParameter("Email"));
			contact.setContactAddress(request.getParameter("Address"));
			contact.setContactPhone(request.getParameter("Phone"));
			cdii.updateContact(contact);

		}
		return true;

	}

	@Override
	public void listContact(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {
		User user=(User)httpSession.getAttribute("user1");
		
		ArrayList<Contact> al = cdii.contactList(user.getUserID());
		
		request.setAttribute("ContactList", al);
		rd = request.getRequestDispatcher("ContactList.jsp");

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	public boolean signupUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ControllerServiceInterfaceImpl");
		String userName = request.getParameter("Name");
		String userPassword = request.getParameter("Password");
		String userEmail = request.getParameter("Email");
		String userPhone = request.getParameter("Phone");
		User user = new User(userName, userPassword, userEmail, userPhone);
		System.out.println(user);
		if (udii.signUp(user))
			return true;
		return false;

	}

	public boolean loginUser(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws ServletException, IOException {
		String Email = request.getParameter("Email");
		String password = request.getParameter("Password");
		
        User user=udii.loginUser(Email, password);
        httpSession.setAttribute("user1", user);
		if (user!= null) {
			return true;
		}
		return false;

	}

	public void listUsers(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<User> al = udii.listUser();
		
		request.setAttribute("UserList", al);
		rd = request.getRequestDispatcher("UserList.jsp");

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	public boolean updateUser(HttpServletRequest request, HttpServletResponse response) {
		
		// TODO Auto-generated method stub
		return false;
	}

	public void logout(HttpServletResponse response,HttpSession session) throws IOException {
		System.out.println(session.toString());
		session.invalidate();
		response.sendRedirect("LoginUser.jsp");
	
		System.out.println(session.getId());
	}

	

}
