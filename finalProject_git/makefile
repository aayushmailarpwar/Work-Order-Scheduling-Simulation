Main.class: Main.java UserAccount.class WorkOrder.class Admin.class HouseKeeping.class Repair.class ITSupport.class
	javac -g Main.java

UserAccount.class: UserAccount.java WorkOrder.class Admin.class ITSupport.class HouseKeeping.class Report.class
	javac -g UserAccount.java

WorkOrder.class: WorkOrder.java 
	javac -g WorkOrder.java

Admin.class: Admin.java WorkOrder.class
	javac -g Admin.java

HouseKeeping.class: HouseKeeping.java WorkOrder.class
	javac -g HouseKeeping.java

ITSupport.class: ITSupport.java WorkOrder.class
	javac -g ITSupport.java

Repair.class: Repair.java WorkOrder.class
	javac -g Repair.java

debug:
	jdb Main

clean: 
	rm *.class

run: Main.class
	java Main
