/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.controladores;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 *
 * @author Yosbel
 */
public class mySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication, request, response);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Builds the target URL according to the logic defined in the main class
     * Javadoc.
     */
    protected String determineTargetUrl(Authentication authentication, HttpServletRequest request, HttpServletResponse res) throws IOException {
        boolean isUser = false;
        boolean isAdmin = false;
        boolean isCliente = false;
        boolean isAnonimo = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("Jurado")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("Administrador")) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("Cliente")) {
                isCliente = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("Anonimo")) {
                isAnonimo = true;
                break;
            }
        }
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        
        Map map = new HashMap();
        map.put("success", true);
        map.put("msg", "Jurado");
      res.getWriter().print(jsonObj.toJson(map));
        User username=(User)authentication.getPrincipal();
        String user=username.getUsername();
        if (isUser) {
            request.getSession().setAttribute("rol", "Jurado");
            request.getSession().setAttribute("username", user);  
            map.put("success", true);
            map.put("msg", "Jurado");
           res.getWriter().print(jsonObj.toJson(map));
            return "/index.htm";
        } else if (isAdmin) {
            request.getSession().setAttribute("rol", "Administrador");
            request.getSession().setAttribute("username", user); 
            map.put("success", true);
            map.put("msg", "Administrador");
            res.getWriter().print(jsonObj.toJson(map));
            return "/index.htm";
        } else if (isCliente) {
            request.getSession().setAttribute("rol", "Cliente");
            request.getSession().setAttribute("username", user); 
            map.put("success", true);
            map.put("msg", "Cliente");
           res.getWriter().print(jsonObj.toJson(map));
            return "/index.htm";
        }  else if (isAnonimo) {
            request.getSession().setAttribute("rol", "Anonimo");
            request.getSession().setAttribute("username", user); 
            map.put("success", true);
            map.put("msg", "Anonimo");
           res.getWriter().print(jsonObj.toJson(map));
            return "/index.htm";
        } else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
