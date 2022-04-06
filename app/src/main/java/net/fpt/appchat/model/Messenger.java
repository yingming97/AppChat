package net.fpt.appchat.model;

public class Messenger {
    private String noiDung,mailSent;
    public static final String TB_NAME = "tb_mess";

    public Messenger() {
    }

    public Messenger(String noiDung, String mailSent) {
        this.noiDung = noiDung;
        this.mailSent = mailSent;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getMailSent() {
        return mailSent;
    }

    public void setMailSent(String mailSent) {
        this.mailSent = mailSent;
    }

}
