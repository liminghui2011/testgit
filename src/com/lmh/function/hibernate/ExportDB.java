package com.lmh.function.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/** 
 * 将hbm生成ddl 
 * @author BCH 
 * 
 */  
public class ExportDB {  
  
	public static void main(String[] args) {
    	System.err.println("start");
        //默认读取hibernate.cfg.xml文件  
        Configuration cfr = new Configuration().configure();  
          
        SchemaExport export = new SchemaExport(cfr);  
        export.create(true, true);
        System.err.println("over");
    }  
}
