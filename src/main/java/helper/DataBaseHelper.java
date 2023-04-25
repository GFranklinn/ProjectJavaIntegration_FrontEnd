package helper;

import model.EntityCategory;
import model.EntityLine;
import model.EntityModel;
import util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DataBaseHelper {

    private static Session session;

    public static Session getInstance() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public static List<EntityLine> getListLine() {
        List<EntityLine> listLine = getInstance().createQuery("FROM EntityLine ").list();
        return listLine;
    }

    public static List<EntityCategory> getListCategory(EntityLine cbbLineSelected) {
        List<EntityCategory> listCategory = getInstance().createQuery(String.format("FROM EntityCategory WHERE id_line = '%s'", cbbLineSelected.getId())).list();
        return listCategory;
    }

    public static List<EntityModel> getListModel(EntityCategory entityCategory) {
        List<EntityModel> listModel = getInstance().createQuery(String.format("FROM EntityModel WHERE id_category = '%s'", entityCategory.getId())).list();
        return listModel;
    }

}
