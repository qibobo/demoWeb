package qiye.demo.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		File file = new File("/usr/local/share/ca-certificates");
		if(!file.exists()){
			System.out.println("file not exsit");
		}
		printFiles(file);
		System.out.println("come to me!");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	private void printFiles(File file) throws IOException{
		if(!file.exists()){
			System.out.println("/usr/local/share/ca-certificates does not exist");
		}else{
			for(File f:file.listFiles()){
				System.out.println("file name:" + f.getAbsolutePath());
				BufferedReader bf = new BufferedReader(new FileReader(f));
				String all="";
				String tmp="";
				while((tmp=bf.readLine())!=null){
					all+=tmp;
				}
				System.out.println(all);
				System.out.println("end========");
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
