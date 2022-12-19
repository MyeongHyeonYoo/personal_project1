package companySystemFinal;
 
public class CompanyBasics {
    private String name; //직원의 이름
    private String jobRank; //직원의 직급
    private int basicSalary; //직원의 기본급
    private int bonus; //직원의 수당
    private double taxRate;   //기본급에 따른 세율
    private int monthSalary; //월급
    private String enterYear; //입사년도 
    private String address; //주소 
    private String phoneNum; //전화번호 
    private String employeeNumber; //사원번호 
    

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public int getMonthSalary() {
        monthSalary = (int)((basicSalary + bonus)*(1.0 - taxRate)); //월급 구하는 공식
        return monthSalary;
    }
    
    public double getTaxRate() { // 기본급에 따른 세율 
        if (basicSalary <= 2000000) {
            taxRate = 0.01;
            return taxRate;
        } else if (basicSalary > 2000000 && basicSalary <= 4000000) {
            taxRate = 0.02;
            return taxRate;
        } else {
            taxRate = 0.03;
            return taxRate;
        }
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJobRank() {
        return jobRank;
    }
    public void setJobRank(String jobRank) {
        this.jobRank = jobRank;
    }
    public int getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }
    public int getBonus() {
        return bonus;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }    
    public String getEnterYear() {
		return enterYear;
	}

	public void setEnterYear(String enterYear) {
		this.enterYear = enterYear;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
