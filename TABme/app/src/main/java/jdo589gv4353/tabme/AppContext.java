package jdo589gv4353.tabme;

import android.app.Application;

import java.util.List;

/**
 * Created by Justin on 4/16/2016.
 */

// this class adds global variables to the entire application context
public class AppContext extends Application {
    private String UserFirst;

    public String getUserFirst(){
        return UserFirst;
    }

    public void setUserFirst(String UserFirst) {
        this.UserFirst = UserFirst;
    }

    private String UserLast;

    public String getUserLast(){
        return UserLast;
    }

    public void setUserLast(String UserLast) {
        this.UserLast = UserLast;
    }

    private String UserEmail;

    public String getUserEmail(){
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    private String UserPhone;

    public String getUserPhone(){
        return UserPhone;
    }

    public void setUserPhone(String UserPhone) {
        this.UserPhone = UserPhone;
    }

    private String UserName;

    public String getUserName(){
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    private List<String> Numbers;

    public List<String> getNumbers(){
        return Numbers;
    }

    public void setNumbers(List<String> Numbers) {
        this.Numbers = Numbers;
    }

    private String TextContent;

    public String getTextContent(){
        return TextContent;
    }

    public void setTextContent(String TextContent) {
        this.TextContent = TextContent;
    }

}
