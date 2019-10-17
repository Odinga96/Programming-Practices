public class PersonelFactory {

    private Personnel personnel;

    public void createPersonell(String type, String lastN, String firstN, String middleN, int empID, double salary){
        Employee e1  = new Employee(lastN, firstN, middleN, empID, salary,type);
        getPersonnel().addPersonnel(e1);
    }

    public Personnel getPersonnel() {
        return personnel;
    }
}
