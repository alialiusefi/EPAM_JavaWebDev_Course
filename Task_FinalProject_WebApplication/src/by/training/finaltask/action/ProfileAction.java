package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileAction extends AuthorizedUserAction {

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("authorizedUser");
        if(user != null && session != null){
            request.setAttribute("user",user);
            UserInfoService userInfoService = (UserInfoService)
                    new ServiceFactoryImpl().createService(DAOEnum.USERINFO);
            UserInfo userInfo = userInfoService.findById(user.getId());
            if(userInfo != null)
            {
                request.setAttribute("userinfo",userInfo);
            }
            return null;
        }
        return new Forward("/login.html",true);
    }
}
