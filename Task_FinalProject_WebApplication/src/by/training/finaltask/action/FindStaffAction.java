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

    private static int ROWS_PER_PAGE = 5;
    private static String NUMBER_REGEX = "[1-9]+";

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
                int amountOfPages = amountOfAllStaff % ROWS_PER_PAGE == 0 ?
                        amountOfAllStaff / ROWS_PER_PAGE : amountOfAllStaff / ROWS_PER_PAGE + 1;
                request.setAttribute("amountOfPages", amountOfPages);
                Integer pagenumber = 1;
                pagenumber = validatePageNumber(
                        request.getParameter("page"), amountOfPages);
                int offset = (pagenumber - 1) * ROWS_PER_PAGE;
                List<User> userList = userService.getAllStaff(offset, ROWS_PER_PAGE);
                request.setAttribute("resultUsers", userList);
                List<UserInfo> userInfoList = userInfoService.findAllStaff(offset, ROWS_PER_PAGE);
                request.setAttribute("resultsUserInfo", userInfoList);
                return null;

            } else {
                return new Forward("/login.html", true);
            }
        }
        return null;
    }

    private Integer validatePageNumber(String pageParameter, int amountOfPages) {
        if (pageParameter.matches(NUMBER_REGEX)) {
            Integer pageNumber = Integer.parseInt(
                    pageParameter);
            if (pageNumber <= amountOfPages) {
                return pageNumber;
            } else {
                return 1;
            }
        }
        return 1;
    }
}
