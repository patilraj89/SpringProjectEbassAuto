package com.ebassauto.ctrl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ebassauto.pojo.CalendarDetails;
import com.ebassauto.pojo.CorridorDetails;
import com.ebassauto.pojo.CorridorDetailsPojo;
import com.ebassauto.pojo.DatePickerPojo;
import com.ebassauto.pojo.Tenor;
import com.ebassauto.service.ICalendarDetailsService;
import com.ebassauto.service.ICorridorService;
import com.ebassauto.service.ITenorService;
import com.ebassauto.util.CorridorUtil;

@CrossOrigin
@RestController
public class HomeController {
	
	@Autowired
	private ICalendarDetailsService icalService;
	
	@Autowired
	private ICorridorService iCorridorSer;
	
	@Autowired
	private ITenorService itenorSer;
	
	@GetMapping("/getDateTime")
	private DatePickerPojo getLocalDt() {
		DatePickerPojo dt=new DatePickerPojo();
		LocalDateTime dtTm=LocalDateTime.now();
		dt.setDtTime(dtTm);
		return dt;
	}
	
	
	@GetMapping("/getCal")
	private List<CalendarDetails> getCalDetails(){
		List<CalendarDetails> calDetails=icalService.getCalendarDetails();
		return calDetails;
	}
	
	@GetMapping("/getCorridor/{dt}")
	private List<CorridorDetailsPojo> getAllDataOfCorridor(@PathVariable("dt") String dtVar)throws Exception{
		//Date inputDate1=new SimpleDateFormat("yyyy-MM-dd").parse(dtVar);
		//System.out.println("Passes Dt="+inputDate1);
		List<CorridorDetailsPojo> corridorDtFormat=new ArrayList<CorridorDetailsPojo>();
		Date inputDate=new SimpleDateFormat("yyyy-MM-dd").parse(dtVar);
		//System.out.println("Input date="+inputDate);
		List<CalendarDetails> calDetails=icalService.getCalendarDetails();
		List<Tenor> tenorList=itenorSer.getAllTenorData();
		List<CorridorDetails> cDetails=iCorridorSer.executeCorridor(calDetails, tenorList, inputDate);
		for(CorridorDetails cdDetails:cDetails) {
			CorridorDetailsPojo crDetails=new CorridorDetailsPojo();
			crDetails.setTradeDate(CorridorUtil.getStringDt(cdDetails.getTradeDate()));
			crDetails.setTargetDate(CorridorUtil.getStringDt(cdDetails.getPublicationDate()));
			crDetails.setTenor(cdDetails.getTenor());
			crDetails.setSettlementDate(CorridorUtil.getStringDt(cdDetails.getSettlementDate()));
			crDetails.setTheoriticalMaturityDate(CorridorUtil.getStringDt(cdDetails.getTheoriticalMaturityDate()));
			crDetails.setCorridorStartDate(CorridorUtil.getStringDt(cdDetails.getCorridorStartDate()));
			crDetails.setCorridorEndDate(CorridorUtil.getStringDt(cdDetails.getCorridorEndDate()));
			corridorDtFormat.add(crDetails);
		}
		return corridorDtFormat;
	}

}
