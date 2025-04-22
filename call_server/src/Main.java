import com.yuanhao.manager.dao.entity.BusinessType;
import com.yuanhao.manager.dao.entity.Caller;
import com.yuanhao.manager.service.*;
import com.yuanhao.manager.service.impl.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String[] args) {
//        //adminLogin
        IAdminService adminService = new AdminServiceImpl();
        boolean value = adminService.adminLogin("root123","123456");
        System.out.println(value);
        //callerList
        ICallerService iCallerService = new CallerServiceImpl();
        List<Caller>callerList = new ArrayList<>();
//        callerList = iCallerService.callerList("");
//        for (Caller caller : callerList) {
//            System.out.println(caller);
//        }
//        //callerAdd
//        for (int i = 0; i < 50; i++) {
//            int a = iCallerService.callerAdd(new Caller("101"+i,"123456","test"+i+"test","",new Date(),new Date(),new Date(),"192.168.188.216"));
//            System.out.println(a);
//        }
//        //callerDelete
//        String[] ids = new  String[50];
//        System.out.println("总长为"+ids.length);
//
//        for (int i = 0; i < 50; i++) {
//            ids[i] = "101"+i;
//        }
//        System.out.println(iCallerService.callerDelete(ids));
//        //callerUpdate
//        Caller caller = new Caller(100,"100","update","update","female",new Date(),new Date(),"群?",new Date(),"192.168.188.216");
//        System.out.println(iCallerService.callerUpdate(100,caller));
//
//        //callerPage
//        Page<Caller> page = new Page<Caller>();
//        page = iCallerService.callerPages("",2,5);
//        System.out.println(page.toString()+page.getList().toString()+"===>"+page.getTotalPages());
        IBusinessWindowService BusinessWindowService = new BusinessWindowServiceImpl();
////BusineesWindowAdd
//        System.out.println(businessWindowDao.businessWindowAdd(new BusinessWindow(114,"114","C","贷款")));
////BusinessWindowDelete
//        System.out.println(iBusinessWindowService.businessWindowDelete(34,35));
////BusinessWindowUpdate
//        System.out.println(businessWindowDao.businessWindowUpdate(114,new BusinessWindow(114,"514","C","贷款")));
//    //BusinessWindowPage
//        Page<BusinessWindow> businessWindowPage = BusinessWindowService.businessWindowPages("A",1,2);
//        System.out.println(businessWindowPage.toString()+"==>"+businessWindowPage.getList().toString()+"=====>"+businessWindowPage.getTotalPages());

        IBusinessTypeService businessTypeService = new BusinessTypeServiceImpl();
        System.out.println(businessTypeService.businessTypeAdd(new BusinessType("D2","测试",1000,"测试")));

    }
}