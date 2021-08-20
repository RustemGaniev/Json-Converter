public class Employee {
    public Long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
    }

    public void setIde(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return " \n id : " + id +
                "\n firstName : " + firstName +
                "\n lastName :  " + lastName +
                "\n country :   " + country +
                "\n age :    " + age + "\n";
    }
}

