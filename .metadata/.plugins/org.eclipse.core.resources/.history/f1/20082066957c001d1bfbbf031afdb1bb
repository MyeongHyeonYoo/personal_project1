package companySystemFinal;
 
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
 
public class EzenCompany implements Company {
    ArrayList<CompanyBasics> arr = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    
   
    //시스템 종료 타이머 메소드 
    public void timer(int mTime) {
        try { Thread.sleep(mTime); }  catch (Exception e) {System.out.print(""); };
     }    
    
    @Override
    public void saveWorker() { //관리자 (1. 등록)
        CompanyBasics cb = new CompanyBasics();
        System.out.print("-사원 번호 : ");
        String employeeNumber = input.next();
        System.out.print("-사원 이름 : ");
        String name = input.next();
        System.out.print("-직급 : ");
        String jobRank = input.next();
        System.out.println("-기본급 : ");
        int basicSalary = input.nextInt();
        System.out.println("-수당 : ");
        int bonus = input.nextInt();
        cb.setEmployeeNumber(employeeNumber);
        cb.setName(name);
        cb.setJobRank(jobRank);
        cb.setBasicSalary(basicSalary);
        cb.setBonus(bonus);
        arr.add(cb);
        System.out.println(">>>등록 완료되었습니다.");
    }
    
    
    @Override
    public void modify() {    //수정
        System.out.println("-수정할 사원의 사원 번호를 입력하세요. : ");
        String  employeeNumber = input.next();
        boolean flag = true;
        for (int i = 0 ; i < arr.size(); i++) {
            if (arr.get(i).getEmployeeNumber().equals(employeeNumber)) {
                 flag = false;
                 System.out.print("-변경 사원 번호 : ");
                 employeeNumber = input.next();
                 System.out.print("-변경 직급 : ");
                 String modiJobRank = input.next();
                 arr.get(i).setJobRank(modiJobRank);
                 System.out.print("-변경 기본급 : ");
                 int modiSalary = input.nextInt();
                 arr.get(i).setBasicSalary(modiSalary);
                 System.out.print("-변경 수당  : ");
                 int modiBounus = input.nextInt();
                 arr.get(i).setBonus(modiBounus);
                 System.out.print(">>>수정이 정상적으로 완료되었습니다.");
                 break;
            }
        }
        if (flag) {
            System.out.println(">>>해당 사원이 없습니다.");
        } 
    }

    @Override 
    public void delete() {  //삭제 
       System.out.print("-삭제할 사원의 사원 번호를 입력하세요. : ");
       String employeeNumber = input.next();

       boolean flag = true;
       for (int i = 0 ; i < arr.size(); i++) {
           if (arr.get(i).getEmployeeNumber().equals(employeeNumber)) {
                flag = false;
                arr.remove(i);
                System.out.println(">>>삭제가 정상적으로 완료되었습니다.");
           }
       }
       if (flag) {
           System.out.println(">>>해당 사원이 없습니다.");
       } 
   }
    
    
    @Override
    public void search() { //검색
        System.out.print("-검색할 사원번호를 입력하세요 : ");
        String employeeNumber = input.next();
        boolean flag = true;
        for (int i = 0 ; i < arr.size(); i++) {
            if (arr.get(i).getEmployeeNumber().equals(employeeNumber)) {
                flag = false;
                System.out.println("사원번호 : "+arr.get(i).getEmployeeNumber());
                System.out.println("이름    : "+arr.get(i).getName());
                System.out.println("직급    : "+arr.get(i).getJobRank());
                System.out.printf("기본급   :  %,2d원 \n",arr.get(i).getBasicSalary());
                System.out.printf("수당    :  %,2d원 \n",arr.get(i).getBonus());
                System.out.println("세율    : "+arr.get(i).getTaxRate());
                System.out.printf("월급    : %,2d원  \n",arr.get(i).getMonthSalary());
                break;
            }
        }
        if (flag) {
            System.out.println(">>>해당 사원이 없습니다.");
        }
    }

    
    

   
    @Override
    public void output() { //전체 사원정보 출력
        System.out.println(" ===============================모든 사원 정보 출력================================");
        System.out.println("──────────────────────────────────────────────────────────────────────────────");
        String[] named = {"사원번호", "이름", "직급", "  기본급", "  수당 ", "  세율", "  월급"};
        System.out.printf("|  %-5s |  %-4s |  %-3s |  %-10s|  %-8s|  %-6s   |  %-9s|", named[0], named[1], named[2], named[3],named[4], named[5], named[6]);
        System.out.println("\n──────────────────────────────────────────────────────────────────────────────");
       for(int i = 0 ; i < arr.size(); i++) {
            System.out.printf("|  %-6s |  %-3s  |  %-4s|  %,-8d  |  %,-6d  |  %-4f |  %,-8d |\n", arr.get(i).getEmployeeNumber(),arr.get(i).getName(), arr.get(i).getJobRank(), arr.get(i).getBasicSalary(), arr.get(i).getBonus(), arr.get(i).getTaxRate(),arr.get(i).getMonthSalary()); 
       }
    }
    

    
	@Override
	public void weeklyAllowance() {   // 연차휴가수당 
		System.out.println("-일일 근로 시간 : ");
		int workingHours = input.nextInt();
		System.out.println("-남은 연차 개수 : ");
		int annualLeaveNum = input.nextInt();
        int minimumWage = 9160;
        int result = (minimumWage * workingHours * annualLeaveNum);
	    System.out.printf(">>>연차 휴가 수당은 %,d 원 입니다. \n",result);
	}


	
	@Override
	public void overtimePay() {   // 초과 근무 수당
		System.out.println("-초과 근무 시간 : ");
		int workingHours = input.nextInt();
        double multiple = 1.5;
        int minimumWage = 9160;
        int result = ((int)(minimumWage * multiple) * workingHours);
        System.out.printf(">>>초과 근무 수당은 %,d 원 입니다.",result);
        System.out.println();
	}
	
	
	@Override
	public void annualLeave() {  //연차 개수 및 근무 햇수 
		while(true) {
			System.out.println("-입사날짜 : 예)20150310");
			String enterDate = input.next();
			//입력형식맞지않으면 다시 입력
			if(enterDate == null || enterDate.length()!=8) {
				System.out.println("\n **다시 형식에 맞게 입력해주세요**");
				continue;
			} else {
			while(true) {
			System.out.println("-조회날짜 : 예)20221209");
			String searchDate = input.next();
			if(searchDate == null || searchDate.length()!=8) {
				System.out.println("\n **다시 형식에 맞게 입력해주세요**");
				continue;
			} else {
			int resultDate = getDayDiff(enterDate,searchDate);
				
		
		//연차 개수 구하기 
		int vacationNum = 0;
		if(365<=resultDate && resultDate<1095) {
			vacationNum = 15;
		} else if(1095<=resultDate && resultDate<1825) {
			vacationNum = 16;
		} else if(1825<=resultDate && resultDate<2555) {
			vacationNum = 17;
		} else if(2555<=resultDate && resultDate<3285) {
			vacationNum = 18;
		} else if(3285<=resultDate && resultDate<4015) {
			vacationNum = 19;
		}
		
		//햇수 구하기 
		int countNum = 0;
		countNum = resultDate/365;
		
		//근무 개월수 + 일수 구하기
		String getYear = enterDate.substring(0, 4);
		int getYearNum = Integer.parseInt(getYear);
		String getMonth = enterDate.substring(4, 6);
		int getMonthNum = Integer.parseInt(getMonth);
		String getDay = enterDate.substring(6, 8);
		int getDayNum = Integer.parseInt(getDay);
		
		String getYear2 = searchDate.substring(0, 4);
		int getYearNum2 = Integer.parseInt(getYear2);
		String getMonth2 = searchDate.substring(4, 6);
		int getMonthNum2 = Integer.parseInt(getMonth2);
		String getDay2 = searchDate.substring(6, 8);
		int getDayNum2 = Integer.parseInt(getDay2);
		
		
		LocalDate startDate = LocalDate.of(getYearNum, getMonthNum, getDayNum);
		LocalDate endDate = LocalDate.of(getYearNum2, getMonthNum2, getDayNum2);
		
		Period period = startDate.until(endDate);
		
		int workYears = period.getYears();
		int workMonths = period.getMonths();
		int wordkDays = period.getDays();
		
		
		//출력
		System.out.println("===========================");
		System.out.println(" 총 연차/휴가 개수 : " + vacationNum);
		System.out.println(" 근무 일수 : "+ workYears + "년 " + workMonths + "개월 " + wordkDays + "일");
		System.out.println(" 햇수 : " + countNum + "년 차");
		System.out.println("===========================");
		break;
					}
				}
			} break;
		}
	}
	
	// 날짜 차이 구하기 
	static int getDayDiff(String date1,String date2) { 
		int diff = 0;
		
		int year1 = Integer.parseInt(date1.substring(0, 4));
		int month1 = Integer.parseInt(date1.substring(4, 6))-1;
		int day1 = Integer.parseInt(date1.substring(6, 8));
		int year2 = Integer.parseInt(date2.substring(0, 4));
		int month2 = Integer.parseInt(date2.substring(4, 6))-1;
		int day2 = Integer.parseInt(date2.substring(6, 8));
		
		Calendar newDate1 = Calendar.getInstance();
		Calendar newDate2 = Calendar.getInstance();
		//날짜 초기화
		newDate1.clear();
		newDate2.clear();
		//날짜 설정
		newDate1.set(year1, month1, day1);
		newDate2.set(year2, month2, day2);
		//날짜 차이 구하기
		diff = (int)((newDate1.getTimeInMillis() - newDate2.getTimeInMillis())/(24*60*60*1000));
		int diff2 = Math.abs(diff);
		
		return diff2;
	}
	
	@Override
    public void severancePay() {  // 퇴직금 및 연봉인상율 조회 
      System.out.print("-입사년도 : ");
      int inYear =  input.nextInt();      
      System.out.print("-연봉(만원 단위 입력) :  ");
      int mo =  input.nextInt();      
      System.out.println("-상여금 포함여부(0:포함,1:비포함) : ");
      int moGu = input.nextInt();      
      System.out.println("-근속 년수 : ");
      int year = input.nextInt();
      
      int nMo = 0;
      int totMo = 0;
      int totOut = 0;
      
      for (int i = 0; i < year; i++) {
         int num[] = new int[12];
         
         int j = 0;
         int k = 0;
         int addNum = 0;
         nMo = 0;
         
         System.out.println("----------------------------------------------------------------");
         System.out.print("*"+(i+1) + "연차 기본연봉 : " + mo + " 만원");
         while (j<12) {
            int ran = (int)(Math.random()*100+1);
         
            if (ran >= 80) {
               num[j]=50;
               k++;
            } else if(ran > 80 && ran <=60) {
               num[j] = 30;
               k++;               
            } else if(ran > 60 && ran <=40) {
               num[j] = 10;
               k++;
            }
            else {
               num[j]=0;
            }      
            j++;         
         if (j==6) {
            System.out.println("");
         	}
         }
         
         addNum = (k < 5 ? 0 : (k < 7 && k>=5) ? 1 :(k < 9 && k>=7)? 2:3);
         try {
            totMo += moGu ==0 ? (mo +=nMo) : (moGu ==1 ? mo +nMo : null);
         } catch (Exception e) {
            for (int l = 0; l < 100; l++) { System.out.println();
            System.err.print("연봉인상율에 상여금 포함여부는 '0'과 '1'만 입력 가능!");
            return;
            } 
         }
         System.out.println("\n-" + inYear + "년도 인센티브 : "+ k + "회, 연봉증가율" + (addNum+ 3 )
               + "% 증가(기본 3%)" + (i+1) + "년차에 지급한 연봉:" + (mo+(moGu==1?nMo:0))+"만원");
         System.out.println("----------------------------------------------------------------");
         if (i != year-1) {
            mo+=mo*((3+addNum)*0.01);
         }
         inYear++;
      }
      totOut = ((moGu==0 ? mo : (mo+nMo))/12)*year;
      System.out.println("\n ====>"+year + "년동안 받은 총 급여액 : "+ totMo + "만원, 퇴사년도 : "+ inYear
            + " 년, 퇴직금 : "+ totOut+"만원");
    }
}