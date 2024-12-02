package com.home.tblmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.home.tblmgmt.entity.ColumnInfo;

@Service
public class ColumnInfoService {

    public void compareColumnInfo(List<ColumnInfo> dbList, List<ColumnInfo> screenList) {

        boolean columnChanged = true;
        boolean columnAdded = false;
        boolean columnRemoved = false;
        boolean pkChanged = false;
        boolean dataTypeChanged = false;
        boolean logicalNameChanged = false;
        boolean physicalNameChanged = false;


        for (ColumnInfo screenRow : screenList) {
            boolean foundMatch = false;
            for (ColumnInfo dbRow : dbList) {
                if (screenRow.getRowNo() == dbRow.getRowNo()) {
                    foundMatch = true;

             
                    if (screenRow.isSame(dbRow)) {
                        columnChanged = false;
                        System.out.println("Row " + screenRow.getRowNo() + ": No change");
                    } else {
                        if (screenRow.isPrimaryKey() != dbRow.isPrimaryKey()) {
                            pkChanged = true;
                            System.out.println("Row " + screenRow.getRowNo() + ": Primary key changed");
                        } else if (!screenRow.getDataType().equals(dbRow.getDataType())) {
                            dataTypeChanged = true;
                            System.out.println("Row " + screenRow.getRowNo() + ": Data type changed");
                        } else if (!screenRow.getColumnLogicalName().equals(dbRow.getColumnLogicalName())) {
                            logicalNameChanged = true;
                            System.out.println("Row " + screenRow.getRowNo() + ": Column logical name changed");
                        } else if (!screenRow.getColumnPhysicalName().equals(dbRow.getColumnPhysicalName())) {
                            physicalNameChanged = true;
                            System.out.println("Row " + screenRow.getRowNo() + ": Column physical name changed");
                        } 
                    }
                    
                    break;
                }
            }
            if (!foundMatch) {
                columnAdded = true;
                System.out.println("Row " + screenRow.getRowNo() + ": New row added");
            }
        }

        for (ColumnInfo dbRow : dbList) {
            boolean foundInScreen = screenList.stream().anyMatch(screenRow -> screenRow.getRowNo() == dbRow.getRowNo());
            if (!foundInScreen) {
                columnRemoved = true;
                System.out.println("Row " + dbRow.getRowNo() + ": Row removed");
            }
        }

        //Backup ddl script to a text file
        //Backup data to excel
        if(columnAdded){

            //Create table
            //Insert data base on new table layout
        }else{
            if(columnRemoved){
                //alter column
            }else if(columnChanged){
                if(pkChanged){
                    //drop and alter pk
                }

                if(dataTypeChanged){
                    //alter dataType
                }

                if(logicalNameChanged){
                    //alter logicalNameChanged
                }

                
                if(physicalNameChanged){
                    //alter physicalNameChanged
                }
            }
        }
    }
}
