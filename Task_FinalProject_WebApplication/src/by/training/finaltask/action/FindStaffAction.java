package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindStaffAction extends AuthorizedUserAction {

    private static String NUMBER_REGEX = "[0-9]+";
    private static int PAGE_MULTIPLIER = 2;

    public FindStaffAction() {
        allowedRoles.add(Role.ADMINISTRATOR);
    }


    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null && allowedRoles.contains(user.getUserRole())) {
                UserService userService = (UserService)
                        new ServiceFactoryImpl().createService(DAOEnum.USER);
                UserInfoService userInfoService = (UserInfoService)
                        new ServiceFactoryImpl().createService(DAOEnum.USERINFO);
                int amountOfAllStaff = userService.getAmountOfAllStaff();
                int amountOfPages = amountOfAllStaff % PAGE_MULTIPLIER == 0 ?
                        amountOfAllStaff / PAGE_MULTIPLIER : amountOfAllStaff / PAGE_MULTIPLIER + 1;
                request.setAttribute("amountOfPages", amountOfPages);
                Integer pagenumber = 1;
                pagenumber = validatePageNumber(
                        request.getParameter("page"));
                if (pagenumber == 1) {
                    List<User> userList = userService.getAllStaff(0, PAGE_MULTIPLIER);
                    request.setAttribute("resultUsers", userList);
                    List<UserInfo> userInfoList = userInfoService.findAllStaff(0, PAGE_MULTIPLIER);
                    request.setAttribute("resultsUserInfo", userInfoList);
                    return null;
                } else {
                    int start = (pagenumber * PAGE_MULTIPLIER) - PAGE_MULTIPLIER;
                    int end = (pagenumber * PAGE_MULTIPLIER) + 1;
                    List<User> userList = userService.getAllStaff(start, end);
                    request.setAttribute("resultUsers", userList);
                    List<UserInfo> userInfoList = userInfoService.findAllStaff(start, end);
                    request.setAttribute("resultsUserInfo", userInfoList);
                    return null;
                }

            } else {
                return new Forward("/login.html", true);
            }
        }
        return null;
    }

    private Integer validatePageNumber(String pageParameter) {
        if (pageParameter == null) {
            return 1;
        }
        if (pageParameter.matches(NUMBER_REGEX)) {
            Integer pagenumber = Integer.parseInt(
                    pageParameter);
            if (pagenumber > 0) {
                return pagenumber;
            }
        }
        return 1;
    }

}
