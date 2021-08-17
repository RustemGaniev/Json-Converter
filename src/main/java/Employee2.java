public class Employee2 extends Employee {
    public Long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee2() {
        // Пустой конструктор
    }


//    public Employee2(Long id, String firstName, String lastName, String country, int age) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.country = country;
//        this.age = age;
//    }
    public long getId() {
        return id;
    }
    public void setIde(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String country() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString(){
        return " \n id : " + id +
                "\n firstName : " + firstName +
                "\n lastName :  " + lastName +
                "\n country :   " + country +
                "\n age :    " + age + "\n";
    }
}

