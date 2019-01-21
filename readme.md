Tracking Drivers History and calculate Average Speed

### Assumptions

 - Trips with less than 5mph or greater than 100mph is considered as invalid Trip
 - System accepts trips with valid start and end Time Format(HH:mm)
 - System Considered certain value to be constants and provide ablitiy to change later
 - Each Driver should have a name 
 - Each Trip should have a Start time, End time and distance travelled else skipped.
 - System works best for calculating based on reading a file/multiple file at once. 
 - System's approach of calculating avgSpeed might not be efficient for readings (not entries) that are sporadic
 - Both Commands always contains all possible data for processing


### Approach
  The main Application calls a HistoryTracker (which acts kind of like API layer) interacts with Two main entities of the System (Driver) -- (Trip). I came up with this idea of two entities cause I thought the system in a database perspective (table for driver and Trip). 

  So this approach helped me design the Tracker layer to have 3 functionalties.
  
    - Register Driver (Store)
    - Associate Trip With Driver (Process & Store)
    - Calculate Avg Speed (Process the data)
    
  I thought caclculating the Average Speed after collecting the data would be the best approach. But it might have one drawback - suppose the system is used in a daily basis. Calculating the average speed every day would add up a bottle neck. I considered that beyond the scope of problem.
  
  The System stores the DriverName and Driver object together in a HashMap in order for easy retrival.
  
    
 ### Background & Comments   

  I have used the internet to get some details about building a Maven project and the intricacies of handling Logger and input files from the best recommended approach.
  
  I have experience working with Java for solving Competitive Coding and RESTful programming. THis is my first time creating a Maven project.
  
  I have tried JUnit for the first time and felt comfortable using it. 

  I enjoyed solving this problem as a complete project than doing it as a competitive coding challenge.
  
  Built with Eclipse IDE