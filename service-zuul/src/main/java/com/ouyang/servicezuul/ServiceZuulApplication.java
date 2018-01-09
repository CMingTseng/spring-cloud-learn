package com.ouyang.servicezuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ServiceZuulApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}

	@Component
	public class MyFilter extends ZuulFilter {
		@Override
		public String filterType() {
			return "pre";
		}

		@Override
		public int filterOrder() {
			return 0;
		}

		@Override
		public boolean shouldFilter() {
			return true;
		}

		@Override
		public Object run() {
			RequestContext ctx = RequestContext.getCurrentContext();
			HttpServletRequest request = ctx.getRequest();
			Object accessToken = request.getParameter("token");
			if(accessToken == null) {
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(401);
				try {
					ctx.getResponse().getWriter().write("token is empty");
				}catch (Exception ignored){}

				return null;
			}
			return null;
		}
	}

}
