package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openHomePage() {
        //Будем определять сейчас приложение на home page или нет по стороке поиска searchstring:
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public void initContactCreation() {
        if (!manager.isElementPresent(By.xpath("//*[@id='content']/h1"))) { // //*[contains(text(), 'Edit / add address book entry')]
            click(By.linkText("add new"));
        }
    }

    public boolean isContactPresent() {
        //Можно использовать тот же, что и в GroupHelper, так как у элемента есть чекбокс с  <input type="checkbox" id="1" name="selected[]" value="1" title="Select (Just name )" alt="Select (Just name )" accept="">
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
    }

    public void returnToHomePage () {
        click(By.linkText("home page"));
    }

    public void initAddNextContact () {
        click(By.linkText("add next"));
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.nickname());
        attach(By.name("photo"), contact.photo());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void submitContactModification() {
        click(By.cssSelector("input[value='Update']"));
    }

    public void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
        //closeAlertAfterContactRemoval(); - Выходит assert "no such alert"
        openHomePage();
    }

    public void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlertAfterContactRemoval() {
        manager.driver.switchTo().alert().accept();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContact();
        //closeAlertAfterContactRemoval();
        openHomePage();
    }

    public void removeEmptyContactList() {
        openHomePage();
        if (isContactPresent()) {
            removeAllContacts();
        }
        removeSelectedContact();
        closeAlertAfterContactRemoval();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tbody tr[name='entry']"));
        for ( var tr : trs) {
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getDomAttribute("id");
            var tds = tr.findElements(By.cssSelector("td"));
            var lastName = tds.get(1).getText();
            var firstName = tds.get(2).getText();
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }
}
