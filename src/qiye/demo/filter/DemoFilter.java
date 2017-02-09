package qiye.demo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class DemoFilter
 */
public class DemoFilter implements Filter {

	private List<String> excludePatternList = new ArrayList<String>();

	/**
	 * Default constructor.
	 */
	public DemoFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletResponse httpResp = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String requestUri = httpReq.getRequestURI();
		StringBuffer requestUrl = httpReq.getRequestURL();
		String requestPathInfo = httpReq.getPathInfo();
		String servletPath = httpReq.getServletPath();
		String contextPath = httpReq.getContextPath();
		String scheme = httpReq.getScheme();
		System.out.println("schema:" + scheme + ",uri:" + requestUri + ",url:" + requestUrl + ",path:" + requestPathInfo
				+ ",servletPath:" + servletPath + ",contextPath:" + contextPath);
		if (this.exclude(requestUri)) {
			System.out.println(" you are not my work");
		} else {
			System.out.println("you are my work");
		}
		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String excludePatternStr = fConfig.getInitParameter("excludePaths");
		if (null != excludePatternStr || !"".equals(excludePatternStr)) {
			String[] patterns = excludePatternStr.split(",");
			for (String str : patterns) {
				excludePatternList.add(str);
			}
		}
	}

	private boolean exclude(String path) {
		if (null == path || "".equals(path)) {
			return false;
		} else {
			for (String excludePath : this.excludePatternList) {
				System.out.println("path:" + path + ",   exclude:" + excludePath);
				if (path.contains(excludePath)) {
					return true;
				}
			}
		}
		return false;
	}

}
