package speedpay.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	 private FilterConfig filterConfig;
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			// TODO Auto-generated method stub
			 String encoding=filterConfig.getInitParameter("encoding");
			 request.setCharacterEncoding(encoding);
			 response.setCharacterEncoding(encoding);
			 response.setContentType("text/html,charset=UTF-8");
			 chain.doFilter(request, response);
		}

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			// TODO Auto-generated method stub
			this.filterConfig=filterConfig;
		}
}
