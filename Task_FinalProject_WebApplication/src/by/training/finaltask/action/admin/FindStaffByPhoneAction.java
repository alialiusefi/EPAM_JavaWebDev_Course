package by.training.finaltask.action.admin;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.InvalidFormDataException;
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

public class FindStaffByPhoneAction extends AuthorizedUserAction {

    private static int ROWS_PER_PAGE = 5;
    private static String NUMBER_REGEX = "[1-9]+";
    private static final String CONTACT_REGEX = "^\\+[0-9]{1,15}$";
    private static Logger logger = LogManager.getLogger(FindStaffByPhoneAction.class);

    public FindStaffByPhoneAction()
    {
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User authUser = (User)session.getAttribute("authorizedUser");
            if(authUser != null && allowedRoles.contains(authUser.getUserRole()))
            {
                String phoneParameter = request.getParameter("search");
                long phone;
                try {
                    phone = validatePhone(phoneParameter);
                } catch (InvalidFormDataException e) {
                    Forward forward = new Forward("/user/admin/findstaff.html?page=1");
                    forward.getAttributes().put("message","incorrectNumberFormat");
                    return forward;
                }
                Forward forward = new Forward("/user/admin/findstaff.html?page=1");
                forward.getAttributes().put("searchParameter",phoneParameter);
                UserService userService = (UserService) factory.
                        createService(DAOEnum.USER);
                UserInfoService userInfoService = (UserInfoService)
                        factory.createService(DAOEnum.USERINFO);
                int amountOfAllStaffByPhone =
                        userService.getAmountOfAllStaffByPhone(
                                phone);
                int amountOfPages = amountOfAllStaffByPhone
                        % ROWS_PER_PAGE == 0 ? amountOfAllStaffByPhone
                        / ROWS_PER_PAGE : amountOfAllStaffByPhone / ROWS_PER_PAGE + 1;
                forward.getAttributes().put("amountOfPages", amountOfPages);
                Integer pagenumber = 1;
                pagenumber = validatePageNumber(
                        request.getParameter("page"), amountOfPages);
                int offset = (pagenumber - 1) * ROWS_PER_PAGE;
                List<UserInfo> userInfoList = userInfoService.findAllStaffByPhone(
                        phone, offset, ROWS_PER_PAGE);
                List<User> userList = userService.getAllStaffByPhone(
                        phone,offset,ROWS_PER_PAGE);
                forward.getAttributes().put("resultUsers",userList);
                forward.getAttributes().put("resultsUserInfo",userInfoList);
                forward.getAttributes().put("paginationURL","/user/admin/findstaffbyphone.html");
                return forward;
            } else {
                return new Forward("login.html",true);
            }
        }
        else {
            throw new PersistentException("forbiddenAccess");
        }
    }

    private long validatePhone(String phoneStr) throws InvalidFormDataException
    {
        if(phoneStr != null && phoneStr.matches(CONTACT_REGEX))
        {
            String phonewithoutplus = phoneStr.substring(1);
            return Long.parseLong(phonewithoutplus);
        }
        throw new InvalidFormDataException("incorrectNumberFormat");
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
