#MAKEFILE OF ENDLESSRUNNINGTCF

#DEFINIZIONE DEL COMPILATORE
JC = javac
#IDENTIFICA LA CARTELLA IN CUI CERCARE I FILE
VPATH = ./endlessrunningtcf
#OPZIONI DEL COMPILATORE
JFLAGS = -d ./ -cp ./
#IDENTIFICA TUTTI I FILE DA CUI IL PROGRAMMA DIPENDE
OBJECTS = Point.class Object.class Obstacle.class Character.class ObstacleFactory.class Player.class Game.class

all: $(OBJECTS)
	jar cvfe running-tcf.jar endlessrunningtcf.Game ./endlessrunningtcf/*.class
	$(MAKE) clean

#ISTRUZIONI PER COMPILARE LE VARIE CLASSI
%.class: %.java
	 $(JC) $(JFLAGS) $^
	 
#ISTRUZIONI PER ELIMINARE I FILE *.class
clean: 
	 @echo -e "\033[1;34m"RIMOZIONE DEI BINARI IN CORSO..." \033[0m"
	 $(RM) -r ./endlessrunningtcf
	 @echo -e "\033[1;34m"RIMOZIONE TERMINATA" \033[0m"

#ISTRUZIONI PER LANCIARE IL PROGRAMMA
run:
	java -jar running-tcf.jar