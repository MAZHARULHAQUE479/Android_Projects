package com.tamtoanthang.apps.mobileparking.Model;

public class ContactModel {

    private String ID;
    private String CardNo;
    private String CardId;
    private String CardType;
     private byte[] InImage;
     private byte[] OutImage;
     private String Intime;
     private String OutTime;
     private String price;
    private String CardIdNo;
    private String UserName;
    private String AdminPassword;
    private String UserPassword;
    private String UserINTime;
    private String UsserOutTime;
    private String AdminName;
public ContactModel(String cardId,String cardNo,String cardType){
    this.CardId=cardId;
    this.CardNo=cardNo;
    this.CardType=cardType;
}
    public ContactModel(){}
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }







    public String getIntime() {
        return Intime;
    }

    public void setIntime(String intime) {
        Intime = intime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }



    public void setCardIdNo(String cardIdNo) {
        CardIdNo = cardIdNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }





    public String getCardIdNo() {
        return CardIdNo;
    }



    public byte[] getOutImage() {
        return OutImage;
    }

    public void setOutImage(byte[] outImage) {
        OutImage = outImage;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public byte[] getInImage() {
        return InImage;
    }

    public void setInImage(byte[] inImage) {
        InImage = inImage;
    }

    public String getUserINTime() {
        return UserINTime;
    }

    public void setUserINTime(String userINTime) {
        UserINTime = userINTime;
    }

    public String getUsserOutTime() {
        return UsserOutTime;
    }

    public void setUsserOutTime(String usserOutTime) {
        UsserOutTime = usserOutTime;
    }
}
