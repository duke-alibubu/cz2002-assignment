all:
	javac -Xlint CODE/boundaries/*.java CODE/controllers/*.java CODE/entities/*.java
	java CODE/boundaries/IOInterface
