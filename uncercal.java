///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//AUTHOR: Jonathan Goldstein                                                                                     //
//DATE: 3/11/2024                                                                                                //
//PURPOSE: to allow the user to perform scientific calculations which also provide the new uncertainties         //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//TODO: fix all IOExceptions so that all sub methods throw the IOEXCEPTION to the main function, which when used in this particular case then subsequently passes
//it to be handled by the interpreter
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class uncertaintyPrimitive{
  private String dataName;

  private double data;
  private double uncertainty;
  private double uncertaintyPercent;

  public uncertaintyPrimitive(String dataName, double data, double uncertainty){
    setDataName(dataName);
    setData(data);
    setUncertainty(uncertainty);
    setUncertaintyPercent((uncertainty/data)*100);
  }
  public uncertaintyPrimitive(String dataName){
    setDataName(dataName);
    setData(0.0f);
    setUncertainty(0.0f);
    setUncertaintyPercent(0.0f);
  }

  public double getdata() {
      return data;
  }
  public String getdataName() {
      return dataName;
  }
  public double getuncertainty() {
      return uncertainty;
  }
  public double getuncertaintyPercent() {
      return uncertaintyPercent;
  }
  public void setData(double data) {
      this.data = data;
      uncertaintyPercent = ((uncertainty/data) * 100);
  }
  public void setDataName(String dataName) {
      this.dataName = dataName;
  }
  public void setUncertainty(double uncertainty) {
      this.uncertainty = Math.abs(uncertainty);
      setUncertaintyPercent((uncertainty/data) * 100);
  }
  private void setUncertaintyPercent(double uncertaintyPercent) {
      this.uncertaintyPercent = Math.abs(uncertaintyPercent);
  }
  ////////////////////////////////////////////////////NON OVERLOADED CALCULATIONS//////////////////////////////////////////////////////////
  //create new object type uncertaintyPrimitive where it is stored as the addition of two other given uncertainty objects
  public static uncertaintyPrimitive addUncertainty(uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(in1.dataName + " + " + in2.dataName);
    newPrim.setData(in1.getdata() + in2.getdata());
    newPrim.setUncertainty(in1.getuncertainty() + in2.getuncertainty());
    newPrim.setUncertaintyPercent((newPrim.getuncertainty()/newPrim.getdata()) * 100);

    return newPrim;
  }
  //TODO: FIX ALL NON ADD UNCERTAITY CALCULATION METHODS TO WORK LIKE ABOVE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  //create new object type uncertaintyPrimitive where it is stored as the subtraction of two other given uncertainty objects
  public static uncertaintyPrimitive subUncertainty(uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(in1.dataName + " - " + in2.dataName);
    newPrim.setData(in1.data - in2.data);
    newPrim.setUncertainty(in1.uncertainty + in2.uncertainty);
    newPrim.setUncertaintyPercent((newPrim.uncertainty/newPrim.data) * 100);

    return newPrim;
  }
  //create new object type uncertaintyPrimitive where it is stored as the multiplication of two other given uncertainty objects
  public static uncertaintyPrimitive multUncertainty(uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(in1.dataName + " * " + in2.dataName);
    newPrim.setData(in1.data * in2.data);
    newPrim.setUncertaintyPercent(in1.uncertaintyPercent + in2.uncertaintyPercent);
    newPrim.setUncertainty(newPrim.data * (newPrim.uncertaintyPercent/100));

    return newPrim;
  }
  //create new object type uncertaintyPrimitive where it is stored as the division of two other given uncertainty objects
  public static uncertaintyPrimitive divUncertainty(uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(in1.dataName + " / " + in2.dataName);
    newPrim.setData(in1.data / in2.data);
    newPrim.setUncertaintyPercent(in1.uncertaintyPercent + in2.uncertaintyPercent);
    newPrim.setUncertainty(newPrim.data * (newPrim.uncertaintyPercent/100));

    return newPrim;
  }
////////////////////////////////////////////////////OVERLOADED CALCULATIONS//////////////////////////////////////////////////////////
  //create new object type uncertaintyPrimitive where it is stored as the addition of two other given uncertainty objects
  public static uncertaintyPrimitive addUncertainty(String name, uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(name);
    newPrim.setData(in1.getdata() + in2.getdata());
    newPrim.setUncertainty(in1.getuncertainty() + in2.getuncertainty());
    newPrim.setUncertaintyPercent((newPrim.getuncertainty()/newPrim.getdata()) * 100);

    return newPrim;
  }
  //create new object type uncertaintyPrimitive where it is stored as the subtraction of two other given uncertainty objects
  public static uncertaintyPrimitive subUncertainty(String name, uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(name);
    newPrim.setData(in1.data - in2.data);
    newPrim.setUncertainty(in1.uncertainty + in2.uncertainty);
    newPrim.setUncertaintyPercent((newPrim.uncertainty/newPrim.data) * 100);

    return newPrim;
  }
  //create new object type uncertaintyPrimitive where it is stored as the multiplication of two other given uncertainty objects
  public static uncertaintyPrimitive multUncertainty(String name, uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(name);
    newPrim.setData(in1.data * in2.data);
    newPrim.setUncertaintyPercent(in1.uncertaintyPercent + in2.uncertaintyPercent);
    newPrim.setUncertainty(newPrim.data * (newPrim.uncertaintyPercent/100));

    return newPrim;
  }
  //create new object type uncertaintyPrimitive where it is stored as the division of two other given uncertainty objects
  public static uncertaintyPrimitive divUncertainty(String name, uncertaintyPrimitive in1, uncertaintyPrimitive in2){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(name);
    newPrim.setData(in1.data / in2.data);
    newPrim.setUncertaintyPercent(in1.uncertaintyPercent + in2.uncertaintyPercent);
    newPrim.setUncertainty(newPrim.data * (newPrim.uncertaintyPercent/100));

    return newPrim;
  }
  public uncertaintyPrimitive createCopy(){
    uncertaintyPrimitive newPrim = new uncertaintyPrimitive(this.dataName);
    newPrim.setData(data);
    newPrim.setUncertainty(uncertainty);
    newPrim.setUncertaintyPercent(uncertaintyPercent);
    return newPrim;
  }
  @Override
  public String toString() {

      return "name: " + dataName + "\nuncertaintyPercent: " + Double.toString(uncertaintyPercent) + "\n";
  }
}

class uncercal{
  static private final String SETTEXTRED = "\u001B[31m";
  static private final String SETTEXTGREEN = "\u001B[32m";
  static private final String SETTEXTBOLD = "\u001B[1m";
  static private final String RESETTEXT = "\u001B[0m";
  enum calculatorArgs {
    NEWOBJ,
    MATHEXP,
    NAME,
    FILEPRINT
  }
  static ArrayList<uncertaintyPrimitive> uncertaintyTable = null;
  static Scanner keyboard = null;
  static String filename = "out.txt";
  static String userLine;
  static Boolean fileHasHeader = false;
  public static void main(String[] args) throws IOException{
    uncertaintyTable = new ArrayList<uncertaintyPrimitive>();
    keyboard = new Scanner(System.in);
    System.out.println("\t\tPhysics Calculator");
    System.out.println("\t\tBy: Jonathan Goldstein");
    printCommands();
    //NEWUNCERTAINTY args: NAME, DATA, UNIT
    while(true){
      System.out.printf("> ");
      userLine = keyboard.nextLine();
        if(userLine.contains("am")){
          //AM - Add Measurement
          clearScreen();
          addMeasurement();
          //System.out.println("EXITING MEASUREMENT INPUT MODE");
          clearScreen();
          printCommands();

          continue;
        }
        if(userLine.contains("em")){
          clearScreen();
          editMeasurement();
          clearScreen();
          printCommands();

          continue;
        }
        if(userLine.contains("dm")){
          clearScreen();
          deleteMeasurement();
          clearScreen();
          printCommands();

          continue;
        }
        if(userLine.contains("sf")){
          //sf - set file
          //sets the output file name if the user does not want to use the preset filename
          setFile();
          printCommands();

          continue;
        }

        if(userLine.contains("pc")){
          clearScreen();
          performCalculation();
          //System.out.printf("EXITING CALCULATOR MODE\n");
          clearScreen();
          printCommands();

          continue;
        }

        if(userLine.contains("pt")){
          //PT - Print Table
          clearScreen();
          printTable();
          System.out.println("Press return to continue...");
          keyboard.nextLine();
          clearScreen();
          printCommands();

          continue;
        }
        if(userLine.contains("e")){
          //e - exit

          break;
        }
        if(userLine.contains("c")){
          //c - clear screen
          clearScreen();
          printCommands();

          continue;
        }
        if(userLine.contains("r")){
          //r - reset values
          resetMem();

          clearScreen();
          System.out.println("Memory cleared!\npress enter to continue...");
          keyboard.nextLine();

          clearScreen();
          printCommands();

          continue;
        }

      }

    keyboard.close();
  }

  static void addMeasurement(){
    System.out.println("New Uncertainty args: NAME, DATA, UNIT UNCERTAINTY\t\t");
    System.out.printf("> ");
    userLine = keyboard.nextLine();
    while(!userLine.isBlank()){
      int nameEndIndex, dataStartIndex, dataEndIndex, unitStartIndex;
      nameEndIndex = userLine.indexOf(" ");
      dataStartIndex = nameEndIndex;
      dataEndIndex = userLine.indexOf(" ", dataStartIndex+1);
      unitStartIndex = dataEndIndex;

      if(unitStartIndex == -1 || dataStartIndex == -1 || dataEndIndex == -1){
        System.out.println("ERROR: MISSING ARGUMENT(S)");
        System.out.printf("> ");
        userLine = keyboard.nextLine();

        continue;
      }

      String name = userLine.substring(0, nameEndIndex);
      double data = Double.parseDouble(userLine.substring(dataStartIndex, dataEndIndex));
      double unit;
      if(userLine.indexOf(" ", dataEndIndex+1) == -1){
        unit = Double.parseDouble(userLine.substring(unitStartIndex));
      }
      else{
        unit = Double.parseDouble(userLine.substring(unitStartIndex, userLine.indexOf(" ", dataEndIndex+1)));
      }

      uncertaintyPrimitive newPrim = new uncertaintyPrimitive(name, data, unit);
      uncertaintyTable.add(newPrim);
      System.out.printf("Added: %s %f %f\n", name, data, unit);
      System.out.printf("> ");
      userLine = keyboard.nextLine();
    }
  }
  static void editMeasurement(){
    //em


    userLine = "edit Measurements setup";
    while (true){
      uncertaintyPrimitive tempPrim = null;
      clearScreen();

      //print recent variable(s)
      System.out.printf("Recently added variables:\n");
      for(int i = 0; i < 3; i++){
        if(i == uncertaintyTable.size()){
          //have reached end of table...

          break;
        }
        System.out.printf("%s\n", uncertaintyTable.get(uncertaintyTable.size() - (i + 1)).getdataName());
      }

      System.out.printf("input variable name\n> ");
      userLine = keyboard.nextLine();
      if(userLine.isBlank()){

        break;
      }
      for (int i = 0; i < uncertaintyTable.size(); i++){
        if(uncertaintyTable.get(i).getdataName().compareTo(userLine) == 0){
          //found variable
          tempPrim = uncertaintyTable.get(i);

          break;
        }
      }
      if (tempPrim == null){
        System.out.println("ERROR: could not find variable name " + userLine + ".\nPress enter to continue...\n");
        keyboard.nextLine();

        continue;
      }
      clearScreen();
      System.out.printf("VAR: %s\n", tempPrim.getdataName());
      System.out.printf("input new data (press enter to not change)\ncurrent value : %f\n> ", tempPrim.getdata());
      while(true){
        userLine = keyboard.nextLine();
        if(!userLine.isBlank()){
          //set new value
          try{
            tempPrim.setData(Double.parseDouble(userLine));

            break;
          }
          catch(Exception ex){
            System.out.println("ERROR: invalid input!\npress enter to continue...");
            keyboard.nextLine();

            continue;
          }
        }

        break;
      }
      clearScreen();
      System.out.printf("VAR: %s\n", tempPrim.getdataName());
      System.out.printf("input new uncertainty (press enter to not change)\ncurrent value : %f\n> ", tempPrim.getuncertainty());
      while(true){
        userLine = keyboard.nextLine();
        if(!userLine.isBlank()){
          //set new value
          try{
            tempPrim.setUncertainty(Double.parseDouble(userLine));

            break;
          }
          catch(Exception ex){
            System.out.println("ERROR: invalid input!\npress enter to continue...");
            keyboard.nextLine();

            continue;
          }
        }

        break;
      }
    }

  }
  static void performCalculation(){
    ArrayList<uncertaintyPrimitive> stack = new ArrayList<uncertaintyPrimitive>();
          int stackPointer = 0;
          double literalIn = 0;

          uncertaintyPrimitive newPrim = null;
          String subString;
          calculatorArgs currentOp = calculatorArgs.NEWOBJ;

          //pc - perform calculations
          //input example: (MATH EXPRESSION) `NAME `Y
          // first arg is the reverse polish notation calculations to occur
          // second arg is denoted to start with a `, to which right after is the name
          // third arg is after the name also denoted with a `, this represents whether to write to output file after calculation
          System.out.println("Calculator args: EXPRESSION `VARNAME `FILE?\t\t");
          System.out.printf("> ");
          userLine = keyboard.nextLine();

          while(!userLine.isBlank()){
            Boolean foundVar = false;
            if(userLine.contains(" ")){
              subString = userLine.substring(0, userLine.indexOf(' '));
            }
            else{
              subString = userLine.substring(1);
            }

              switch(currentOp){
              case NEWOBJ:
              currentOp = calculatorArgs.MATHEXP;
              //DEPRECATED, PLEASE REMOVE ASAP!
              //refresh calculator stack otherwise it'll ruin further calculations
              stack.clear();
              stackPointer = 0;

              continue;
              case MATHEXP:
                if(subString.contains("`")){
                  //before moving to next step, store final answer into new object
                  newPrim = (uncertaintyPrimitive)stack.get(stackPointer-1).createCopy();
                  newPrim.setDataName("UNNAMED UNCERTAINTY");
                  currentOp = calculatorArgs.NAME;

                  continue;
                }
                if(subString.compareTo("+") == 0){

                  stack.set(stackPointer-2, uncertaintyPrimitive.addUncertainty(stack.get(stackPointer-2), stack.get(stackPointer-1)));
                  stackPointer--;
                  userLine = userLine.substring(subString.length()+1);

                  continue;
                }
                if(subString.compareTo("-") == 0){

                  stack.set(stackPointer-2, uncertaintyPrimitive.subUncertainty(stack.get(stackPointer-2), stack.get(stackPointer-1)));
                  stackPointer--;
                  userLine = userLine.substring(subString.length()+1);

                  continue;
                }
                if(subString.compareTo("*") == 0){

                  stack.set(stackPointer-2, uncertaintyPrimitive.multUncertainty(stack.get(stackPointer-2), stack.get(stackPointer-1)));
                  stackPointer--;
                  userLine = userLine.substring(subString.length()+1);

                  continue;
                }
                if(subString.compareTo("/") == 0){

                  stack.set(stackPointer-2, uncertaintyPrimitive.divUncertainty(stack.get(stackPointer-2), stack.get(stackPointer-1)));
                  stackPointer--;
                  userLine = userLine.substring(subString.length()+1);

                  continue;
                }
                //...

                //substring must either be a literal or a variable
                //check if is a literal
                try{
                  literalIn = Double.parseDouble(subString);
                  //create temporary uncertainty primitive on stack
                  stack.add(new uncertaintyPrimitive("Input literal", literalIn, 0.0f));
                  stackPointer++;
                  userLine = userLine.substring(subString.length()+1);

                  continue;
                }
                catch(Exception ex) {

                }
                //must be name of variable, check list
                for (int i = 0; i < uncertaintyTable.size(); i++){
                    if(uncertaintyTable.get(i).getdataName().compareTo(subString) == 0){
                      //add current variable onto stack
                      if(stack.size() > stackPointer){
                        stack.set(stackPointer, (uncertaintyPrimitive)uncertaintyTable.get(i).createCopy());
                      }
                      else{
                        stack.add((uncertaintyPrimitive)uncertaintyTable.get(i).createCopy());
                      }
                        stackPointer++;
                      foundVar = true;

                      break;
                    }
                }
                if(!foundVar){
                  System.out.printf("ERROR: do not know command or variable %s\n> ", subString);
                  userLine = keyboard.nextLine();

                  break;
                }
                else{
                  foundVar = !foundVar;
                }
                //remove current sub argument
                userLine = userLine.substring(subString.length()+1);

                //keep processing math input until reach ` char,
                //when done, remove math expression from string using ` loc
                //offset then set currentOp to Name
                break;
              case NAME:
              newPrim.setDataName(userLine.substring(1, userLine.lastIndexOf('`') - 1));
              userLine = userLine.substring(subString.length()+1);
              currentOp = calculatorArgs.FILEPRINT;

              break;
              case FILEPRINT:
              //check if user desires current values to be stored in data within file
              if(subString.compareTo("y") == 0){
                //user has selected yes, to print into file
                if(!fileHasHeader){
                  try{
                    writeFileHeader();
                  }catch(IOException e){
                    System.out.printf("ERROR: unable to open file for writing!\n");
                  }
                  fileHasHeader = true;

                }
                //first write header to file
                FileWriter tempFilePoint = null;
                try{
                  tempFilePoint = new FileWriter(filename, true);
                }
                catch(IOException e){
                  System.out.printf("ERROR: could not open file for writing.\ncontinuing...");
                }
                  PrintWriter tempWriteOut = new PrintWriter(tempFilePoint);
                  tempWriteOut.printf("%14s,\t%,14.2f,\t%,14.2f,\t%,14.2f%c\n", newPrim.getdataName(), newPrim.getdata(), newPrim.getuncertainty(), newPrim.getuncertaintyPercent(), '%');
                  tempWriteOut.close();
              }


              //store value into uncertainty main table
              uncertaintyTable.add(newPrim);
              //if y in final argument, print to set file, otherwise ignore

              if(newPrim != null){
                System.out.printf("Success!\nStored value in variable: %s\n> ", newPrim.getdataName());
              }
              else{
                System.out.printf("ERROR: FAILED TO INITIALIZE NEW UNCERTAINTY OBJECT!\n");
              }
              newPrim = null;
              userLine = keyboard.nextLine();
              currentOp = calculatorArgs.NEWOBJ;

              break;
            }
          }
  }
  static void printTable(){
    System.out.printf("All uncertainty percentages:\n%15s\t%15s\t%15s\t%15s\n\n", "NAME", "DATA", "UNCERTAINTY", "PERCENTAGE");
          for(int i = 0; i < uncertaintyTable.size(); i++){
            System.out.printf("%14s,\t%,14.2f,\t%,14.2f,\t%,14.2f%c\n", uncertaintyTable.get(i).getdataName(), uncertaintyTable.get(i).getdata(), uncertaintyTable.get(i).getuncertainty(), uncertaintyTable.get(i).getuncertaintyPercent(), '%');
          }
  }
  static void setFile() throws IOException{
    //first let the user use cmd file explorer
    while(true){
      File selected = new File(fileExplorer());
      if(selected.exists() && selected.isFile()){
        filename = selected.getAbsolutePath();
        //set filehasheader to false since either the user is starting a new table in the file or has just created the file
        fileHasHeader = false;

        break;
      }
      clearScreen();
      System.out.println(SETTEXTRED + SETTEXTBOLD + "ERROR: did not select proper file!  press enter to continue." + RESETTEXT);
      keyboard.nextLine();
    }

    clearScreen();

    return;
  }
  //returns an absolute path to the file or folder
  private static String fileExplorer(){
    //first, print the current folder of the file
    Boolean searching = true;
    File tempCursor = new File(System.getProperty("user.dir"));
    File allFiles[] = tempCursor.listFiles();
    while(searching){
      clearScreen();
      System.out.printf(SETTEXTGREEN + SETTEXTBOLD + "File Explorer:\n\n\n" + RESETTEXT);
      for(int i = 0; i < allFiles.length; i++){
        System.out.printf("%s\n", allFiles[i].getName());
      }
      //ask for filename, ../, or absolute file path
      System.out.printf("\n> ");
      userLine = keyboard.nextLine();
      if(userLine.compareTo("createfile") == 0){
        //create new file and return the path
        String newDir = tempCursor.getPath();
        System.out.printf("New filename: ");
        tempCursor = new File(newDir + File.separatorChar + keyboard.nextLine());
        try{
          tempCursor.createNewFile();
        }
        catch(IOException e){
          System.out.printf("ERROR: could not create file!\n");
        }
        if(tempCursor.exists()){
          return tempCursor.getAbsolutePath();
        }
      }
      if(userLine.compareTo("createfolder") == 0){
        //create new folder and enter into folder
        String newDir = tempCursor.getPath();
        System.out.printf("new folder name: ");
        tempCursor = new File(newDir + File.separatorChar + keyboard.nextLine());
        if(tempCursor.mkdirs()){
          allFiles = tempCursor.listFiles();
        }
        else{
          tempCursor = new File(newDir);
        }
      }
      if(userLine.compareTo(".") == 0){
        return tempCursor.getAbsolutePath();
      }
      if(userLine.compareTo("..") == 0){
        //user is trying to go back one folder
        String newDir = tempCursor.getPath().concat(File.separatorChar + "..");
        tempCursor = new File(newDir);
        allFiles = tempCursor.listFiles();

        continue;
      }
      for(int i = 0; i < allFiles.length; i++){
        if(allFiles[i].getName().compareTo(userLine) == 0){
          //this must be the file the user is talking about
          if(allFiles[i].isFile()){
            return allFiles[i].getAbsolutePath();
          }
          else{
            String newDir = tempCursor.getPath().concat(File.separatorChar + allFiles[i].getName());
            tempCursor = new File(newDir);
            allFiles = tempCursor.listFiles();

            break;
          }
        }
      }
      //isn't in current directory
    }
    return null;
  }
  static void clearScreen(){
    //use ansi escape sequences since most OSs support them
    System.out.print("\033[H\033[2J");
  }
  static void resetMem(){
    uncertaintyTable.clear();

  }

  static void deleteMeasurement(){
    uncertaintyPrimitive tempPrim = null;
    while(true){
      clearScreen();
      //print recent variable(s)
      System.out.printf("Recently added variables:\n");
      for(int i = 0; i < 3; i++){
        if(i == uncertaintyTable.size()){
          //have reached end of table...

          break;
        }
        System.out.printf("%s\n", uncertaintyTable.get(uncertaintyTable.size() - (i + 1)).getdataName());
      }
      System.out.printf("input variable name\n> ");
      userLine = keyboard.nextLine();
      if(userLine.isBlank()){

        break;
      }
      for (int i = 0; i < uncertaintyTable.size(); i++){
        if(uncertaintyTable.get(i).getdataName().compareTo(userLine) == 0){
          //found variable
          //create copy temporarily to get name and to show that it was found
          tempPrim = uncertaintyTable.get(i).createCopy();
          uncertaintyTable.remove(i);

          break;
        }
      }
      if (tempPrim == null){
        System.out.println("ERROR: could not find variable name " + userLine + ".\nPress enter to continue...\n");
        keyboard.nextLine();

        continue;
      }
      System.out.printf("Successfully emoved var %s!\npress enter to continue...", tempPrim.getdataName());
      tempPrim = null;
      keyboard.nextLine();
    }
  }

  static void printCommands(){

    System.out.println("commands:\nam - add measurement\nem - edit measurement\ndm - delete measurement\npc - perform calculations\npt - print table\nsf - set file\nc - clear screen\nr - reset internal table\ne - exit");
  }

  static void writeFileHeader() throws IOException{
    FileWriter temp = new FileWriter(filename, true);
    PrintWriter tempBuffered = new PrintWriter(temp);
    tempBuffered.printf("All uncertainty percentages:\n%15s\t%15s\t%15s\t%15s\n\n", "NAME", "DATA", "UNCERTAINTY", "PERCENTAGE");
    tempBuffered.close();
  }
}
//TODO: look for all filewriter and printwriter locations and make sure all of them have their close method called!
