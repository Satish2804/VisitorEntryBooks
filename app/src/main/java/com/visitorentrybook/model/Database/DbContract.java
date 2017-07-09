package com.visitorentrybook.model.Database;
/*
 * Created by Satish on 21-05-2017.
 */

import android.provider.BaseColumns;

final class DbContract {

    private DbContract() {

    }

    /* Inner class that defines the security table contents */
    static class SecurityTableFields implements BaseColumns {

        static final String SECURITY_DETAILS_TABLE = "SecurityDetails";
        /* Security Guard Columns */
        static final String S_Id = "S_Id", S_Name = "S_Name", S_Email = "S_Email", S_Phone = "S_Phone",
                S_Photo = "S_Photo", S_Address = "S_Address", Added_AdminId = "Added_AdminId",
                Created_Date = "Created_Date", S_Status = "S_Status",
                Last_UpdatedDate = "Last_UpdatedDate", S_Password = "S_Password";
    }

    /* Inner class that defines the visitor table contents */
    static class VisitorTableFields implements BaseColumns {

        static final String VISITOR_DETAILS_TABLE = "VisitorDetails";

        /* Visitor Guard Columns */
        static final String V_Id = "V_Id", V_SecurityId = "V_SecurityId", V_Name = "V_Name", V_Photo = "V_Photo",
                V_Phone = "V_Phone", V_Email = "V_Email", V_CompanyName = "V_CompanyName", V_ComingFrom = "V_ComingFrom",
                V_EmployeeName = "V_EmployeeName", V_EmployeeEmail = "V_EmployeeEmail", V_EmployeePhone = "V_EmployeePhone",
                V_ProofType = "V_ProofType", V_ProofId = "V_ProofId", V_DeviceName = "V_DeviceName",
                V_DeviceCompany = "V_DeviceCompany", V_DeviceNumber = "V_DeviceNumber", V_CheckInTime = "V_CheckInTime",
                V_CheckOutTime = "V_CheckOutTime", V_HoursOfVisit = "V_HoursOfVisit", V_Status = "V_Status";
    }

}
