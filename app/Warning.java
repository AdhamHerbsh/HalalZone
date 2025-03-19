public class Warning {
    private int id;
    private String businessEmail;
    private String note;
    private String date;

    public Warning(int id, String businessEmail, String note, String date) {
        this.id = id;
        this.businessEmail = businessEmail;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }
}
