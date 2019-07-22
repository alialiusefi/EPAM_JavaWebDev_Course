package by.training.finaltask.filter;



import by.training.finaltask.action.*;
import by.training.finaltask.action.admin.AddStaffAction;
import by.training.finaltask.action.admin.FindStaffAction;
import by.training.finaltask.action.admin.FindStaffByFirstNameAction;
import by.training.finaltask.action.admin.FindStaffByPhoneAction;
import by.training.finaltask.action.staff.AddPetAction;
import by.training.finaltask.action.staff.DeletePetAction;
import by.training.finaltask.action.staff.EditPetAction;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionURIFilter implements Filter {

    private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/register", RegisterAction.class);
        actions.put("/user/profile", ProfileAction.class);
        actions.put("/user/userdelete", UserDeleteAction.class);
        actions.put("/user/useredit",UserEditAction.class);
        actions.put("/user/admin/addstaff", AddStaffAction.class);
        actions.put("/user/admin/findstaff", FindStaffAction.class);
        actions.put("/user/admin/findstaffbyfirstname", FindStaffByFirstNameAction.class);
        actions.put("/user/admin/findstaffbyphone", FindStaffByPhoneAction.class);
        actions.put("/pets/staff/addpet", AddPetAction.class);
        actions.put("/pets/staff/editpet", EditPetAction.class);
        actions.put("/pets/findpet", FindPetAction.class);
        actions.put("/pets/moreinfopet",MoreInfoPetAction.class);
        actions.put("/pets/staff/deletepet", DeletePetAction.class);
        actions.put("/pets/findpetbybreed", FindPetByBreedAction.class);
        actions.put("/pets/findpetbyshelter", FindPetByShelterAction.class);
        actions.put("/pets/findpetbybirthdate", FindPetByBirthDateAction.class);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)
    throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            System.out.println(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Class<? extends Action> actionClass = actions.get(actionName);
            try {
                Action action = actionClass.newInstance();
                action.setName(actionName);
                httpRequest.setAttribute("action", action);
                clearSessionMessage(httpRequest);
                chain.doFilter(request, response);
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                System.out.println("It is impossible to create action handler object " + e);
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }
        } else {
            System.out.println("It is impossible to use HTTP filter");
        }
    }

    private void clearSessionMessage(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            session.setAttribute("message",null);
        }
    }

    @Override
    public void destroy() {}

}
