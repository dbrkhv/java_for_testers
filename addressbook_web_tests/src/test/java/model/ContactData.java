package model;

public record ContactData(String id, String firstName, String middleName, String lastName, String nickname,
                          String company, String address, String homePhone, String mobilePhone, String workPhone,
                          String fax, String email, String email2, String email3) {

    public ContactData () {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData("", firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData("", this.firstName, middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData("", this.firstName, this.middleName, lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withNickname (String nickname) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withCompany (String company) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withAddress (String address) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withMobilePhone (String mobilePhone) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, mobilePhone, this.workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withWorkPhone (String workPhone) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, workPhone, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withFax (String fax) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, fax, this.email, this.email2, this.email3);
    }

    public ContactData withEmail (String email) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, email, this.email2, this.email3);
    }

    public ContactData withEmail2 (String email2) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, email2, this.email3);
    }

    public ContactData withEmail3 (String email3) {
        return new ContactData("", this.firstName, this.middleName, this.lastName, this.nickname, this.company, this.address,
                this.homePhone, this.mobilePhone, this.workPhone, this.fax, this.email, this.email2, email3);
    }
}
