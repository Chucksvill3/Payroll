package a2_dnp279;

public class Bookkeeping {
	public interface PayableEntity {
	    double amountOwed();
	}
	
	public abstract class Employee implements PayableEntity{
		private static int numEmployees;
		private String name;
		private double bonus;
		
		public Employee(String name) {
		    this.name = name;
		    numEmployees++;
		}

		public abstract double calcPrebonusPay();
		public abstract String getJobCode();
		public static int numEmployees() {
			return numEmployees;
			
		}
		
		public double calcTotalPay() {
			
			return calcPrebonusPay() + bonus;
			
		}
		
		public double amountOwed() {
			return calcTotalPay();
		}
		
		public String getName() {
			return name;
		}
		
		public double getBonus() {
			return bonus;
		}
		public String toString() {
	        String preBonusPay = String.format("%8.2f", calcPrebonusPay());
	        String totalPay = String.format("%8.2f", calcTotalPay());
	        return String.format("%-15s %4s $%s $%s", name, getJobCode(), preBonusPay, totalPay);
	    }
		
		
		public class HourlyEmployee extends Employee {
		    private double hourlyWage;
		    private double hours;

		    public HourlyEmployee(String name, double hourlyWage) {
		        super(name);  // Call Employee constructor
		        this.hourlyWage = hourlyWage;
		    }

		    public void setHoursWorked(double hours) {
		        this.hours = hours;
		    }

		    @Override
		    public double calcPrebonusPay() {
		        if (hours <= 40) {
		            // If the employee works 40 hours or less, pay is hours * hourlyWage[^1^][1]
		            return hours * hourlyWage;
		        } else {
		            // If the employee works more than 40 hours, pay is 40 * hourlyWage + 1.5 * hourlyWage for extra hours[^1^][1][^2^][2]
		            return 40 * hourlyWage + (hours - 40) * 1.5 * hourlyWage;
		        }
		    }


		    @Override
		    public String getJobCode() {
		    	return("HRLY");
		    }
		}
				public class SalariedEmployee extends Employee{
					private double weeklyPay;
					
				
				public SalariedEmployee(String name, double weeklypay){
					super(name);
					this.weeklyPay = weeklyPay;
					
					
				}
				@Override
			    public double calcPrebonusPay() {
			        return weeklyPay; 
				}

			    @Override
			    public String getJobCode() {
			    	return("SLRY");
			    }
			
	  }
				
				public class CommissionEmployee extends Employee {
				    private double rate;
				    private double amount;

				    public CommissionEmployee(String name, double rate) {
				        super(name);
				        this.rate = rate;
				    }

				    public void setSales(double amount) {
				        this.amount = amount;
				    }

				    @Override
				    public double calcPrebonusPay() {
				        return rate * amount;  // Pay is a percentage of sales
				    }

				    @Override
				    public String getJobCode() {
				        return "COMM";
				    }
				    public class LimitedCommissionEmployee extends CommissionEmployee {
				        private double basePay;

				        public LimitedCommissionEmployee(String name, double rate, double basePay) {
				            super(name, rate);
				            this.basePay = basePay;
				        }

				        @Override
				        public double calcPrebonusPay() {
				            double commissionPay = super.calcPrebonusPay();
				            // Pay is at least base pay and no more than twice the base pay
				            if (commissionPay < basePay) {
				                return basePay;
				            } else if (commissionPay > 2 * basePay) {
				                return 2 * basePay;
				            } else {
				                return commissionPay;
				            }
				        }

				        @Override
				        public String getJobCode() {
				            return "LCOM";
				        }
				    }

				    
				    
				}
				
				
			

	}
}
		

