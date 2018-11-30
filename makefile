#
# makefile 
#
# input file for 'make' build tool ( /usr/bin/make )
# to build solution for JUnit sample
#
# @author Dillon Carns 
# @version 11/13/2018
#

JUNIT4_JAR = /usr/share/java/junit-4.10.jar
JUNIT4_RUNNER = org.junit.runner.JUnitCore 
HAMCREST_JAR = /usr/share/java/hamcrest/core-1.1.jar
CKSTYLE_COMMAND =  -jar /usr/local/checkstyle-5.5/checkstyle-5.5-all.jar
CKSTYLE_XML = style.xml

default: 
	@echo "usage: make target"
	@echo "7 available targets: clean - removes editor tmpfiles and .class files"
	@echo "____________________ compile, test - builds JUnit5 tests, runs all (4 and 5)"
	@echo "____________________ defchk, customchk - default or custom checkstyle"
	@echo "Just starting with make? Try these 4 make commands successively:"
	@echo "________ make clean ; make compile ; make test ; make defchk"

# makefile syntax
#target-name: files dependent on (can use multiple lines by ending
#             lines with \
#<TAB char>Unix command-line command
#<TAB char>Unix command-line command
#etc.
#Essential that command lines start with single TAB character

compile: GithubScraper.java GithubDriver.java GitObject.java Issues.java Commits.java GithubScraperTest.java \
          $(JUNIT4_JAR)
	javac -cp .:$(JUNIT4_JAR) GithubScraperTest.java
	javac GithubScraper.java GitObject.java Issues.java Commits.java GithubDriver.java

clean:
	rm -f *~
	rm -f *.class

run: GithubDriver.class
	java GithubDriver

test: GithubScraper.class GithubScraperTest.class 
	java -cp .:$(JUNIT4_JAR):$(HAMCREST_JAR) $(JUNIT4_RUNNER) GithubScraperTest

#defchk: GithubScraper.java $(CKSTYLE_XML)
#	java $(CKSTYLE_COMMAND) -c $(CKSTYLE_XML) GithubScraper.java

#customchk: GithubScraper.java style.xml
#	java $(CKSTYLE_COMMAND) -c style.xml GithubScraper.java

style.xml:
	@echo "Custom checkstyle needs a local style.xml file."
	@echo "Copy cs_appstate_checks.xml into style.xml and edit as needed."
	@echo "--------------------------------------------------------------"

