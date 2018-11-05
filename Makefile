all:
	javac CODE/boundaries/*.java CODE/controllers/*.java CODE/entities/*.java
	java CODE/boundaries/IOInterface

clean:
	rm CODE/boundaries/*.class CODE/controllers/*.class CODE/entities/*.class
