package bo;

import bo.custom.impl.*;
import dao.util.BoType;


import static com.sun.java.accessibility.util.EventID.ITEM;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case USERS:return (T) new UsersBoImpl();
        }
        return null;
    }
}
