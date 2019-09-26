package a6test;

import comp401fall16.Grader;

public class A6JediGrader {
  public A6JediGrader() {}
  
  public static void main(String[] args) {
    String[] test_names = A6JediTest.getTestNames();
    String[] test_descriptions = A6JediTest.getTestDescriptions();
    
    int num_tests = test_names.length;
    
    Grader[] graders = new Grader[num_tests];
    for (int i = 0; i < num_tests; i++) {
      graders[i] = new Grader(A6JediTest.class, 
        test_names[i], test_descriptions[i], 3.0D / num_tests);
    }
    
    Grader.grade(graders);
  }
}
