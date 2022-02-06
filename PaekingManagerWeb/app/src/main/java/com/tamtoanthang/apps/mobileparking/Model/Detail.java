package com.tamtoanthang.apps.mobileparking.Model;

/**
 * Created by lue on 06-10-2017.
 */

public class Detail {

    private String CardId;
    private String CardNo;
    private String CardType;
    private String CardPrice;
    private String InImage;
    private String OutImage;
    private String InTime;
    private String OutTime;
    private String Status;
    private String UserName;

    public String getCardId() {
        return CardId;
    }
    public void setCardId(String CardId) {
        this.CardId = CardId;
    }
    public String getCardNo() {
        return CardNo;
    }
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }
    public String getCardType() {
        return CardType;
    }
    public void setCardType(String CardType) {
        this.CardType = CardType;
    }
    public String getCardPrice()
    {
        return CardPrice;
    }
    public void setCardPrice(String CardPrice )
    {
        this.CardPrice=CardPrice;
    }
    public String getInImage()
    {
        return InImage;
    }
    public void setInImage(String InImage)
    {
        this.InImage=InImage;
    }
    public String getOutImage()
    {
        return OutImage;
    }
    public void setOutImage(String OutImage )
    {
        this.OutImage=OutImage;
    }
    public String getInTime()
    {
        return InTime;
    }
    public void setInTime(String InTime)
    {
        this.InTime=InTime;
    }
    public String getOutTime()
    {
        return OutTime;
    }
    public void setOutTime(String OutTime )
    {
        this.OutTime=OutTime;
    }
    public String getStatus()
    {
        return Status;
    }
    public void setStatus(String Status)
    {
        this.Status=Status;
    }
    public String getUserName()
    {
        return UserName;
    }
    public void setUserName(String UserName)
    {
        this.UserName=UserName;
    }
}
