package at.ac.tuwien.big.we16.ue3.controller;

import at.ac.tuwien.big.we16.ue3.CheckRegistrationData;
import at.ac.tuwien.big.we16.ue3.model.User;
import at.ac.tuwien.big.we16.ue3.service.AuthService;
import at.ac.tuwien.big.we16.ue3.service.UserService;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final CheckRegistrationData checkRegData;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
        this.checkRegData = new CheckRegistrationData();
    }

    public void getRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.authService.isLoggedIn(request.getSession())) {
            response.sendRedirect("/");
            return;
        }
        request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
    }

    // TODO validation of user data
    public void postRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String dateOfBirth = request.getParameter("dateofbirth");
        String eMail = request.getParameter("email");
        String password = request.getParameter("password");

        if(!checkRegData.checkFirstName(firstname) || !checkRegData.checkLastName(lastname) || !checkRegData.checkBirthDate(dateOfBirth) ||
                !checkRegData.checkBirthDate18(dateOfBirth) || !checkRegData.checkEMail(eMail) || !checkRegData.checkPassword(password)){

            JsonObject json = new JsonObject();
            json.addProperty("firstnameValid", checkRegData.checkFirstName(firstname));
            json.addProperty("lastnameValid", checkRegData.checkLastName(lastname));
            json.addProperty("dateofbirthValid", checkRegData.checkBirthDate(dateOfBirth));
            json.addProperty("dateofbirth18Valid", checkRegData.checkBirthDate18(dateOfBirth));
            json.addProperty("emailValid", checkRegData.checkEMail(eMail));
            json.addProperty("passwordValid", checkRegData.checkPassword(password));

            request.setAttribute("JSON", json);

            request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
            return;
        }

        // TODO User erstellen, in Datenbank speichern und zur OverviewSeite weiterleiten
        User user = new User();
        this.userService.createUser(user);
        this.authService.login(request.getSession(), user);
        response.sendRedirect("/views/overview.jsp");
        request.getRequestDispatcher("/views/overview.jsp").forward(request, response);
    }

}
