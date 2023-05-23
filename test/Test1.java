import com.tedu.trip.dao.impl.CaCategoryDaoImpl;
import com.tedu.trip.entity.Category;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        fun1();
    }

    private static void fun1(){
        CaCategoryDaoImpl categoryDao = new CaCategoryDaoImpl();
        List<Category> list = categoryDao.selectAll();
        for (Category category : list) {
            System.out.println(category);
        }
    }
}
