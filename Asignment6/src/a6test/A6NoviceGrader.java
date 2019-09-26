package a6test;

import comp401fall16.Grader;

public class A6NoviceGrader {
  public A6NoviceGrader() {}
  
  public static void main(String[] args) {
    String[] test_names = A6NoviceTest.getTestNames();
    String[] test_descriptions = A6NoviceTest.getTestDescriptions();
    
    int num_tests = test_names.length;
    
    Grader[] graders = new Grader[num_tests];
    for (int i = 0; i < num_tests; i++) {
      graders[i] = new Grader(A6NoviceTest.class, 
        test_names[i], test_descriptions[i], 4.0D / num_tests);
    }
    
    Grader.grade(graders);
  }
}
