package dao;
import entity.ClientInformation;
import hbUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OperationsDAO {

    HibernateUtil hbUtil = new HibernateUtil();

    public void depositMoney(double money, String selectedIban) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String sql = "UPDATE client_account SET client_balance = client_balance + :money WHERE client_iban = :selectedIban";
            NativeQuery<?> query = session.createNativeQuery(sql, void.class);
            query.setParameter("money", money);
            query.setParameter("selectedIban", selectedIban);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void withdrawMoney(double money, String selectedIban){
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try(session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE client_account SET client_balance = client_balance - :money WHERE client_iban = :selectedIban";
            NativeQuery<?> query = session.createNativeQuery(sql, void.class);
            query.setParameter("money", money);
            query.setParameter("selectedIban", selectedIban);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ClientInformation getClientInformation(String password) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        ClientInformation clientInformation = null;

        try(session) {
            transaction = session.beginTransaction();
            String hql = "FROM ClientInformation ci " +
                    "LEFT JOIN FETCH ci.accounts " +
                    "WHERE ci.clientPassword = :password";
            clientInformation = session.createQuery(hql, ClientInformation.class)
                    .setParameter("password", password)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return clientInformation;
    }

    public boolean checkPassword(String password) {
        Session session = hbUtil.getSessionFactory().openSession();
        try(session) {
            String hql = "SELECT COUNT(ci) > 0 FROM ClientInformation ci WHERE ci.clientPassword = :password";
            return session.createQuery(hql, Boolean.class)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Double> getBalance(int clientId) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction;
        List<Double> balances = null;

        try(session) {
            transaction = session.beginTransaction();
            String sql = "SELECT client_balance FROM client_account WHERE client_id = :clientId";
            NativeQuery<Double> query = session.createNativeQuery(sql, Double.class);
            query.setParameter("clientId", clientId);
            balances = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return balances;
    }


    public void moneyTransferBetweenAccounts(String recipientIban, String senderIban, double money) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try(session) {
            transaction = session.beginTransaction();
            // money sender
            String sql1 = "UPDATE client_account SET client_balance = client_balance - :money WHERE client_iban = :senderIban";
            NativeQuery<Double> query1 = session.createNativeQuery(sql1, Double.class);
            query1.setParameter("money", money);
            query1.setParameter("senderIban", senderIban);
            query1.executeUpdate();
            // money receiver
            String sql2 = "UPDATE client_account SET client_balance = client_balance + :money WHERE client_iban = :recipientIban";
            NativeQuery<Double>  query2 = session.createNativeQuery(sql2, Double.class);
            query2.setParameter("money", money);
            query2.setParameter("recipientIban", recipientIban);
            query2.executeUpdate();

            transaction.commit();
        }catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
