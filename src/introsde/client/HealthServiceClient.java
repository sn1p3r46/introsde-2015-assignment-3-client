package introsde.client;

import introsde.ws.HealthServiceImplementationService;
import introsde.ws.HealthServiceInterface;
import introsde.ws.Person;
import introsde.ws.HealthMeasureHistory;
import introsde.handlers.PrettyStringPrinter;
import introsde.ws.MeasureDefinition;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.xml.datatype.DatatypeFactory;

import java.util.List;

public class HealthServiceClient{

    static HealthServiceImplementationService healthServiceImplementationService = new HealthServiceImplementationService();
    static HealthServiceInterface healthServiceInterface = healthServiceImplementationService.getHealthServiceImplementationPort();
    private static Long deleteID = 0L;
    private static HealthMeasureHistory hmh_static = null;

    public static void main(String[] args) throws Exception {
        //HealthServiceImplementationService healthServiceImplementationService = new HealthServiceImplementationService();
        //HealthServiceInterface healthServiceInterface = healthServiceImplementationService.getHealthServiceImplementationPort();
        readPersonList();
        readPerson();
        updatePerson();
        createPerson();
        removePerson();
        readPersonHistory();
        readMeasureTypes();
        readPersonMeasure();
        savePersonMeasure();
        updatePersonMeasure();

    }

    static void readPersonList(){
        headerPrinter(1);
        functionPrinter("readPersonList()");

        List<Person> personList = healthServiceInterface.readPersonList();
        if(personList!=null){
            for(Person p : personList){
                System.out.println(p.getFirstname());
            }
        }else{
            System.out.println("The personList is NULL");
        }
    }

    static void readPerson(){
        headerPrinter(2);
        functionPrinter("readPerson(1L)");

        Person person = healthServiceInterface.readPerson(1L);
        if(person!=null){
                System.out.println(person.getFirstname());
        }else{
            System.out.println("The person is NULL");
        }
    }

    static void updatePerson(){
        headerPrinter(3);
        functionPrinter("updatePerson(1L)");

        Person person = healthServiceInterface.readPerson(1L);

        System.out.println(person.getFirstname());
        person.setFirstname( new StringBuffer(person.getFirstname()).reverse().toString());
        healthServiceInterface.updatePerson(person);
        person = healthServiceInterface.readPerson(1L);
        if(person!=null){
                System.out.println(person.getFirstname());
        }else{
            System.out.println("The person is NULL");
        }
    }

    static void createPerson() throws Exception {
        headerPrinter(4);
        functionPrinter("createPerson(p)");
        Person p = new Person();
        p.setFirstname("Mario");
        p.setLastname("Marioni");
        p.setBirthday(30,12,1992);
        p = healthServiceInterface.createPerson(p);
        deleteID = (long) p.getIdPerson();
        System.out.println("\n Created new person: ");
        System.out.println("\n NAME: " + p.getFirstname());
        System.out.println("\n SURNAME: " + p.getLastname());
        System.out.println("\n BIRTHDAY: " + p.getBirthday());

    }

    static void removePerson(){
        headerPrinter(5);
        functionPrinter("removePerson("+ deleteID+")");
        Person person = healthServiceInterface.readPerson(deleteID);
        if(person!=null){
            System.out.println("PERSON with id "+ deleteID +" not null deleting...");
            healthServiceInterface.deletePerson(deleteID);
        }
        person = healthServiceInterface.readPerson(deleteID);
         if(person==null){
             System.out.println("Person DELETED");
         }
    }

    static void readPersonHistory(){
        headerPrinter(6);
        functionPrinter("readPersonHistory(1L,height)");
        List<HealthMeasureHistory> hmhList = healthServiceInterface.readPersonHistory(1L,"height");
        Person p = healthServiceInterface.readPerson(1L);
        //System.out.println(hmhList.size());
        System.out.println("PERSON FULL NAME: " + p.getFirstname() + " " + p.getLastname() );
        System.out.println("MEASURE TYPE: height");
        for (HealthMeasureHistory hmh : hmhList){
            System.out.println("\n\nMID: " + hmh.getIdMeasureHistory());
            System.out.println("VALUE: " + hmh.getValue());
            System.out.println("TIMESTAMP: " + hmh.getTimestamp());
        }
    }

    static void readMeasureTypes(){
        headerPrinter(7);
        functionPrinter("readMeasureTypes()");
        List<MeasureDefinition> mdList = healthServiceInterface.readMeasureTypes();
        for(MeasureDefinition md : mdList){
            System.out.println(md.getMeasureName());
        }
    }

    static void readPersonMeasure(){
        headerPrinter(8);
        functionPrinter("readPersonMeasure(1L,height,1L)");
        Person p = healthServiceInterface.readPerson(2L);
        if(p==null){
            System.out.println("Person null");
        }
        Long idPerson_L = (long) p.getIdPerson();
        Long mid_L = (long) healthServiceInterface.readPersonHistory(2L,"height").get(0).getIdMeasureHistory();
        System.out.println(idPerson_L + " " + mid_L);

        HealthMeasureHistory hmh = healthServiceInterface.readPersonMeasure(idPerson_L,"height",mid_L);
        System.out.println("MID:" + hmh.getIdMeasureHistory());
        System.out.println("MEASURE NAME: " + hmh.getMeasureDefinition().getMeasureName());
        System.out.println("VALUE: " + hmh.getValue());
        System.out.println("TIMESTAMP: " + hmh.getTimestamp());
    }

    static void savePersonMeasure(){
        headerPrinter(9);
        functionPrinter("savePersonMeasure(1L, Measure)");
        MeasureDefinition md = new MeasureDefinition();
        md.setMeasureName("height");
        HealthMeasureHistory hmh = new HealthMeasureHistory();
        hmh.setValue("122");
        hmh.setMeasureDefinition(md);
        hmh = healthServiceInterface.savePersonMeasure(1L,hmh);
        hmh_static = hmh;
        System.out.println("MEASURE CREATED!!!");
        System.out.println("MID: "+hmh.getIdMeasureHistory());
        System.out.println("TIMESTAMP: "+hmh.getTimestamp());
        System.out.println("MEASURENAME: "+hmh.getMeasureDefinition().getMeasureName());
        System.out.println("VALUE: " + hmh.getValue());
    }


    static void updatePersonMeasure() throws Exception {
        headerPrinter(9);
        functionPrinter("updatePersonMeasure(1L, Measure)");
        Long idPerson = 1L;
        Long mid = (long) hmh_static.getIdMeasureHistory();
        System.out.println("MID: " + mid);
        System.out.println("MEASURE TYPE: " + hmh_static.getMeasureDefinition().getMeasureName());
        System.out.println("OLD TIMESTAMP: "+hmh_static.getTimestamp());
        System.out.println("OLD VALUE: " + hmh_static.getValue());
        System.out.println("ID PERSON: " + idPerson);
        hmh_static.setValue(new StringBuffer(hmh_static.getValue()+"10").reverse().toString());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1921);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(cal.getTime());

        hmh_static.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));

        HealthMeasureHistory hmh = healthServiceInterface.updatePersonMeasure(idPerson,hmh_static);

        System.out.println("\n==>VALUES UPDATED:");

        System.out.println("MID: " + hmh.getIdMeasureHistory());
        System.out.println("MEASURE TYPE: " + hmh.getMeasureDefinition().getMeasureName());
        System.out.println("NEW TIMESTAMP: "+hmh.getTimestamp());
        System.out.println("NEW VALUE: " + hmh.getValue());
        System.out.println("ID PERSON: " + idPerson);
    }


    static void headerPrinter(int i){
        String s = PrettyStringPrinter.stringFormatter(PrettyStringPrinter.HEADER, i);
        System.out.println(s);
    }

    static void functionPrinter(String func){
        String s = PrettyStringPrinter.stringFormatter(PrettyStringPrinter.FUNCTION, func);
        System.out.println(s);
    }
}
