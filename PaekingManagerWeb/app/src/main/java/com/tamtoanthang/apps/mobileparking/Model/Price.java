package com.tamtoanthang.apps.mobileparking.Model;

/**
 * Created by lue on 31-10-2017.
 */

public class Price {

    private String Id;
    private String CardId;
    private String CardType;

    public String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }
    public String getCardId() {
        return CardId;
    }
    public void setCardId (String CardId) {
        this.CardId = CardId;
    }

    public String getCardType()
    {
        return CardType;
    }
    public void setCardType(String CardType )
    {
        this.CardType=CardType;
    }


}
