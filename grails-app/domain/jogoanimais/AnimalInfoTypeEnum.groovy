package jogoanimais

enum AnimalInfoTypeEnum
{
	
	ANIMAL(1),
	ACTION(2)
	/*
	
  	1("ANIMAL"), 
  	2("ACTION")
*/
   	final int value
    AnimalInfoTypeEnum(int value) {
      this.value = value
    }
    int value() { value } 
}