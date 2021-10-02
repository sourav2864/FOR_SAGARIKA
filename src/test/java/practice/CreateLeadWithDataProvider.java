package practice;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.Vtiger.GenericUtility.BaseClass;
import com.crm.Vtiger.elementRepsitory.CreateLeadPage;
import com.crm.Vtiger.elementRepsitory.HomePage;
import com.crm.Vtiger.elementRepsitory.LeadInfoPage;
import com.crm.Vtiger.elementRepsitory.LeadPage;

public class CreateLeadWithDataProvider extends BaseClass{
	@DataProvider
	public Object [] [] getData() throws EncryptedDocumentException, IOException{
		return eLib.getExcelDataProvider("createLeadPage");
	} 
	
	@Test(dataProvider = "getData")
	public void createLeadWithMultipleData(String Last_Name, String Company, String Industry_type) {
		// navigate to lead 
		HomePage hp = new HomePage(driver);
		hp.clickOnleads();
		
		// click on crate new lead
		LeadPage lp = new LeadPage(driver);
		lp.clickOnCreateLead();
		
		//create lead with multiple data
		CreateLeadPage clp = new CreateLeadPage(driver);
		clp.createLeadWithMultipleDataProvider(Last_Name, Company, Industry_type);
		clp.clickOnSavePageBtn();
		
		// validate
		LeadInfoPage lip = new LeadInfoPage(driver);
		String actualName = lip.lastNameInfo();
		Assert.assertTrue(actualName.contains(Last_Name));
		System.out.println(Last_Name);
		
		String actualCompanyName = lip.companyNameInfo();
		Assert.assertTrue(actualCompanyName.contains(Company));
	}	
}

