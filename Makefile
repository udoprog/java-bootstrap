JAR=$(CURDIR)/target/java-bootstrap-0.0.1-SNAPSHOT.jar
JAVA=java -cp $(JAR)

all:
	mvn package
	@echo
	$(JAVA) se.tedro.bootstrap.CoffeeMaker101
	@echo
	$(JAVA) se.tedro.bootstrap.CoffeeMakerGuice
	@echo
	$(JAVA) se.tedro.bootstrap.CoffeeMakerSpring
	@echo
	$(JAVA) se.tedro.bootstrap.CoffeeMakerDagger
