package appstu.util;

import java.util.Vector;

public class ProduceMaxBh {
    
    public String getMaxBh(String sqlStr, String whereID) {
        System.out.println("ProduceMaxBh.sqlStr :" + sqlStr);
        appstu.util.RetrieveObject reobject = new RetrieveObject();
        Vector vdata = null;
        Object obj = null;
        vdata = reobject.getObjectRow(sqlStr);
        obj = vdata.get(0);
        String maxbh = null, newbh = null;
        
        if (obj == null) {
            newbh = whereID + "01";
        } else {
            maxbh = String.valueOf(vdata.get(0));
            System.out.println("maxbh = " + maxbh);
            String subStr = String.valueOf(Integer.parseInt(maxbh) + 1);
            System.out.println(subStr);
            if ((subStr.length() == 1) || (subStr.length() == 3) || (subStr.length() == 5)) {
                subStr = "0" + subStr;
                newbh = subStr;
            } else {
                newbh = whereID + subStr;
            }
            System.out.println("substr = " + subStr);
            System.out.println("newbh = " + newbh);
        }
        return newbh;
    }
}
