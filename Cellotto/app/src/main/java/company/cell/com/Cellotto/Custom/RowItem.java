package company.cell.com.Cellotto.Custom;

/**
 * Created by lue on 05-08-2017.
 */

public  class RowItem {

    private String play;
    private String win ;
    private String play_digit;
    private String win_digit;

    public RowItem(String play, String win, String play_digit,
                   String win_digit) {

        this.play = play;
        this.win = win;
        this.play_digit = play_digit;
        this.win_digit = win_digit;
    }

    public String getplay() {
        return play;
    }

    public void setplay(String play) {
        this.play = play;
    }

    public String getwin() {
        return win;
    }

    public void setwin(String win) {
        this.win = win;
    }

    public String getPlay_digit() {
        return play_digit;
    }

    public void setPlay_digit(String status) {
        this.play = status;
    }

    public String getWin_digit() {
        return win_digit;
    }

    public void setWin_digit(String contactType) {
        this.win_digit = contactType;
    }

}
