package com.tamtoanthang.apps.mobileparking.Model;

/**
 * Created by lue on 28-10-2016.
 */
public class Contact {
    private int _id;
    private byte[] InImage;
    private byte[] OutImage;
    private String price;
    private String CardIdNo;
    private String CardNo;

    private String OutTime;
    private String InTime;
    private String Status;
    private String userName;
    private String In_ImagePath;
    private String Out_ImagePath;



    // Empty constructor


    // constructor
    public Contact( String CardIdNo, String price, byte[] InImage, byte[] OutImage,String Status,String InTime,String OutTime ,String CardNo,String user,String in_ImagePath,String out_image) {

        this.CardIdNo = CardIdNo;
        this.price = price;
        this.InImage=InImage;
        this.OutImage=OutImage;
        this.Status=Status;
        this.InTime=InTime;
        this.OutTime=OutTime;
        this.CardNo=CardNo;
        this.userName=user;
        this.In_ImagePath=in_ImagePath;
        this.Out_ImagePath=out_image;

        //this.InTime=InTime;

    }
    public Contact( String CardIdNo, String price, byte[] InImage, byte[] OutImage) {

        this.CardIdNo = CardIdNo;
        this.price = price;
        this.InImage=InImage;
        this.OutImage=OutImage;
         this.Status=Status;
        //this.InTime=InTime;

    }
    public Contact(){}

 public Contact(int i, byte[] InImage,String CardIdNo, String price , byte[] OutImage,String Status) {
     this._id=i;
     this.InImage=InImage;
     this.CardIdNo = CardIdNo;
     this.price = price;
     this.OutImage=OutImage;
     this.Status=Status;
                  }





    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int keyId) {
        this._id = keyId;
    }

    public byte[] getInImage() {
        return InImage;
    }

    public void setInImage(byte[] inImage) {
        InImage = inImage;
    }

    public byte[] getOutImage() {
        return OutImage;
    }

    public void setOutImage(byte[] outImage) {
        OutImage = outImage;
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

    public void setCardIdNo(String cardIdNo) {
        CardIdNo = cardIdNo;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getIn_ImagePath() {
        return In_ImagePath;
    }

    public void setIn_ImagePath(String in_ImagePath) {
        In_ImagePath = in_ImagePath;
    }

    public String getOut_ImagePath() {
        return Out_ImagePath;
    }

    public void setOut_ImagePath(String out_ImagePath) {
        Out_ImagePath = out_ImagePath;
    }


    // getting name

}
