Main.class: Main.java UserAccount.class WorkOrder.class Admin.class HouseKeeping.class Repair.class ITSupport.class
	javac -g Main.java

UserAccount.class: UserAccount.java WorkOrder.class Admin.class ITSupport.class HouseKeeping.class Repair.class
	javac -g UserAccount.java

WorkOrder.class: WorkOrder.java Admin.class HouseKeeping.class ITSupport.class Repair.class 
	javac -g WorkOrder.java

Admin.class: Admin.java HouseKeeping.class ITSupport.class Repair.class
	javac -g Admin.java

HouseKeeping.class: HouseKeeping.java
	javac -g HouseKeeping.java

ITSupport.class: ITSupport.java
	javac -g ITSupport.java

Repair.class: Repair.java
	javac -g Repair.java

debug:
	jdb Main

clean: 
	rm *.class

run: Main.class
	java Main
