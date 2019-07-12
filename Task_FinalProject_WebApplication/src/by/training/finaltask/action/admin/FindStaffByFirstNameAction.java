package by.training.finaltask.action.admin;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.action.LoginAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindStaffByFirstNameAction extends AuthorizedUserAction {

    private static Logger logger = LogManager.getLogger(FindStaffByFirstNameAction.class);


    private static int ROWS_PER_PAGE = 5;
    private static String NUMBER_REGEX = "[1-9]+";

    public FindStaffByFirstNameAction() {
        allowedRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null && allowedRoles.contains(user.getUserRole())) {
                String firstnameParameter = request.getParameter(
                        "search");
                Forward forward = new Forward("/user/admin/findstaff.html?page=1");
                forward.getAttributes().put("searchParameter",firstnameParameter);
                UserService userService = (UserService) new ServiceFactoryImpl().createService(DAOEnum.USER);
                UserInfoService userInfoService = (UserInfoService)
                        new ServiceFactoryImpl().createService(DAOEnum.USERINFO);
                firstnameParameter = "%" + firstnameParameter + "%";
                int amountOfAllStaffByFirstName = userService.getAmountOfAllStaffByFirstName(firstnameParameter);
                int amountOfPages = amountOfAllStaffByFirstName % ROWS_PER_PAGE == 0 ?
                        amountOfAllStaffByFirstName / ROWS_PER_PAGE : amountOfAllStaffByFirstName / ROWS_PER_PAGE + 1;
                forward.getAttributes().put("amountOfPages", amountOfPages);
                Integer pagenumber = 1;
                pagenumber = validatePageNumber(
                        request.getParameter("page"), amountOfPages);
                int offset = (pagenumber - 1) * ROWS_PER_PAGE;
                List<UserInfo> userInfoList = userInfoService.findAllStaffByFirstName(
                        firstnameParameter, offset, ROWS_PER_PAGE);
                List<User> userList = userService.getAllStaffByFirstName(
                        firstnameParameter,offset,ROWS_PER_PAGE);
                forward.getAttributes().put("resultUsers",userList);
                forward.getAttributes().put("resultsUserInfo",userInfoList);
                forward.getAttributes().put("paginationURL","/user/admin/findstaffbyfirstname.html");
                return forward;
            }
        } else {
            throw new PersistentException("forbiddenAccess");
        }
        return null;
    }

    private int validatePageNumber(String pageParameter, int amountOfPages) {
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
