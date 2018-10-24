package com.oracle.rsys.CommonPages;

import com.oracle.rsys.Base.BasePage;

public class RsysGlobalSearch extends BasePage{
	
	String searchString;
	
	
	public void fnSearchForProgramsWithText(String searchString1) {
		this.searchString=searchString1;
		System.out.println("Searching for programs with entered text --> " + searchString);	
		
	}
	

	public void fnSearchForCampaignsWithText(String searchString1) {
		this.searchString=searchString1;
		System.out.println("Searching for Campaigns with entered text --> " + searchString);	
		
	}
	
	
	public void fnSearchForAudienceWithText(String searchString1) {
		this.searchString=searchString1;
		System.out.println("Searching for Audience with entered text --> " + searchString);	
		
	}
	


	}
