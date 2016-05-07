package at.ac.tuwien.big.we16.ue3;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name="CheckRegistrationData")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class CheckRegistrationData {

    @WebMethod(operationName="checkFirstNameFunction")
    @WebResult(name="isFirstNameValid")
    public boolean checkFirstName(String firstName){
        if(firstName.length() == 0){
            return false;
        }
        return true;
    }

    @WebMethod(operationName="checkLastNameFunction")
    @WebResult(name="isLastNameValid")
    public boolean checkLastName(String lastName){
        if(lastName.length() == 0){
            return false;
        }
        return true;
    }

    @WebMethod(operationName="checkBirthDateFunction")
    @WebResult(name="isBirthDateValid")
    public boolean checkBirthDate(String birthDate){
        return false;
    }

    @WebMethod(operationName="checkEMailFunction")
    @WebResult(name="isEMailValid")
    public boolean checkEMail(String eMail){
        return false;
    }

    @WebMethod(operationName="checkPasswordFunction")
    @WebResult(name="isPasswordValid")
    public boolean checkPassword(String password){
        if(password.length() < 4 || password.length() > 8){
            return false;
        }
        return true;
    }

    @WebMethod(operationName="checkAGBFunction")
    @WebResult(name="isAGBValid")
    public boolean checkAGB(boolean isChecked){
        return isChecked;
    }
}
