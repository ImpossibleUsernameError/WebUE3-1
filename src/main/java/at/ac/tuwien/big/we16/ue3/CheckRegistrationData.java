package at.ac.tuwien.big.we16.ue3;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckRegistrationData {

    public boolean checkFirstName(String firstName){
        if(firstName.length() <= 0){
            return false;
        }
        return true;
    }

    public boolean checkLastName(String lastName){
        if(lastName.length() <= 0){
            return false;
        }
        return true;
    }

    public boolean checkBirthDate(String birthDate){

        boolean valid = false;

        try{
            new SimpleDateFormat("dd.mm.yyyy").parse(birthDate);
            valid = true;
        } catch(ParseException pe){
            if(!valid){
                valid = false;
            }
        }

        try{
            new SimpleDateFormat("dd/mm/yyyy").parse(birthDate);
            valid = true;
        } catch(ParseException pe){
            if(!valid){
                valid = false;
            }
        }

        try{
            new SimpleDateFormat("dd-mm-yyyy").parse(birthDate);
            valid = true;
        } catch(ParseException pe){
            if(!valid){
                valid = false;
            }
        }

        return valid;
    }

    public boolean checkBirthDate18(String birthDate){
        if(!birthDate.equals("")) {
            Date date = null;

            try {
                if (date == null) {
                    date = new SimpleDateFormat("dd.mm.yyyy").parse(birthDate);
                }
            } catch (ParseException pe) {
                date = null;
            }

            try {
                if (date == null) {
                    date = new SimpleDateFormat("dd/mm/yyyy").parse(birthDate);
                }

            } catch (ParseException pe) {
                date = null;
            }

            try {
                if (date == null) {
                    date = new SimpleDateFormat("dd-mm-yyyy").parse(birthDate);
                }

            } catch (ParseException pe) {
                date = null;
            }

            Date date18YearsBefore = new Date(18 * 365 * 24 * 60 * 60 * 1000);

            if (date.after(new Date()) || date.before(date18YearsBefore)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEMail(String eMail){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(eMail);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public boolean checkPassword(String password){
        if(password.length() < 4 || password.length() > 8){
            return false;
        }
        return true;
    }
}
