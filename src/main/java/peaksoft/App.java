package peaksoft;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import peaksoft.entity.Student;
import peaksoft.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Student student = new Student("Akylbek", "Nurbekov", "java", 21);
        Student student1 = new Student("Аза", "Kasymaov", "javaScript", 19);
        Student student2 = new Student("Аза", "Jakypov", "Python", 22);
        Student student3 = new Student("Амантур", "Askarbeko", "C++", 20);
        Student student4 = new Student("Актан", "Omrurzakov", "Java", 21);
        Student student5 = new Student("Аза", "Kanatov", "javaScript", 25);
        Student student6 = new Student("Аза", "Jyrgalbekov", "java", 33);
        Student student7 = new Student("Аза", "Jyrgalbekov", "java", 12);
        Student student8 = new Student("Аза", "Jyrgalbekov", "java", 20);
        Student student9 = new Student("Aza", "Jyrgalbekov", "java", 30);



//        getUsersAza();              // 20 жаштан чон Аза ларды чыгаруу методу
//        deleteUserName("Актан");      // Аты менен очуруу
//        saveUsers(student9);                // User лерди бд га салуу
//        System.out.println(deleteById(1));              // Id си менен очуруу



    }

    // 20 жаштан чон Аза ларды чыгаруу методу
    public static List<Student> getUsersAza() {
        List<Student>list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list  = session.createQuery("from Student where name = 'Аза' and age > 20  ").getResultList();
            session.getTransaction().commit();
            System.out.println("жашы 20 ойдо Азалар чыкты!");
            session.close();
            for (Student s:list) {
                System.out.println(s);
            }
        } catch (HibernateException h) {
            h.getMessage();
            System.out.println("Азалар чыкпады!");
        }
            return list;
    }
    // Аты менен очуруу
    public static void deleteUserName(String name) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE * from student WHERE name = ?");
            session.getTransaction().commit();
            System.out.println("Человек по имени '" + name + "' удален!");
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
            System.out.println("Человек по имени '" + name + "' не удален!");
        }
    }
    // User лерди бд га салуу
    public static void saveUsers(Student student) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Базага кошулду!!");
            session.close();
        } catch (HibernateException h) {
            h.getMessage();
            System.out.println("Базага кошулган жок!!");
        }

    }
    // Id си менен очуруу
    public static Student deleteById(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("Человек с id " + id + " удален!");
            session.close();
            return student;
        }catch (HibernateException e){
            e.getMessage();
            System.out.println("Чедовек с id " + id + " не удален!");
        }
        return null;
    }
}

